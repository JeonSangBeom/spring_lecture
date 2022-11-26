    const dateUL = $(".calendar .dates ul");
	
	const dayList = ["SUN","MON","TUE","WED","THU","FRI","SAT"];
	
    const now = new Date(); // 오늘 날짜...  new Date() 생성자를 통해서만 객체를 생성할 수 있다.
    let pickedNow = new Date(); // 클릭했을때 넘어갈 날짜...
    let firstDay = new Date(now.getFullYear(), now.getMonth(), 1); // 현재 날짜의 월에서 1일을 기준으로 새로운 date 생성
    const leapYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 윤년
    const nonLeapYear = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 윤년아님
    let selectYear;
	
    const btnNextMonth = $(".calendar .header .next");
    const btnPrevMonth = $(".calendar .header .prev");

    const txtYear = $(".calendar .header .year");
    const txtMonth = $(".calendar .header .month");

    const calendar = $(".calendar");
	
	let queryDate=""; // ajax에서 사용할 것(날짜 선택시 변경되는 것)
	
    //const inputDate = document.querySelector(".inputDate");

    btnPrevMonth.on("click", function () {
      pickedNow = new Date(pickedNow.getFullYear(), pickedNow.getMonth() - 1, 1);
      makeCalendar(pickedNow.getFullYear(), pickedNow.getMonth());
    });
    btnNextMonth.on("click", function () {
      pickedNow = new Date(pickedNow.getFullYear(), pickedNow.getMonth() + 1, 1);
      makeCalendar(pickedNow.getFullYear(), pickedNow.getMonth());
    });
    makeCalendar(pickedNow.getFullYear(), pickedNow.getMonth());
    
    // 시작하자마자 쿼리 데이트 생성(시작했을때 리스트 보이게 만들기)
    queryDate = now.getFullYear()+addZero(now.getMonth()+1)+addZero(now.getDate());
    // list불러오기(아래)
    const sendData = {
		pickedDate:queryDate
	}
	$.ajax({
		url:"List.do",
		data:JSON.stringify(sendData),
		contentType:"application/json",
		dataType:"json",
		method:"POST",
		success:function(res){
			$(".todoList").html("");
			$.each(res.todoList,function(idx,item){
				$(".todoList").append(`<li data-no="${item.no}" class="${item.done}">
					<span class="txt">${item.todo}</span>
					<button class="btnDelete"><span class="material-icons">delete</span></button>
				</li>`)
			});
		},
		error:function(err) {
			console.log(err);
		}
	});
    //inputDate.value = `${now.getFullYear()} / ${addZero(now.getMonth() + 1)} / ${addZero(now.getDate())}`;
    function makeCalendar(pYear, pMonth) {
      //윤년 공식 4로 떨어지면 윤년,100년 단위는 윤년 아님, 400으로 떨어지면 윤년
      let output = "";
      let count = 1;
      firstDay = new Date(pYear, pMonth, 1);
      txtYear.text( firstDay.getFullYear() );
      txtMonth.text( addZero(firstDay.getMonth() + 1) );
      if (firstDay.getFullYear() % 4 === 0) {
        if (firstDay.getFullYear() % 100 === 0) {
          selectYear = nonLeapYear;
        } else {
          selectYear = leapYear;
        }
      } else {
        selectYear = nonLeapYear;
      }
      if (firstDay.getFullYear() % 400 === 0) {
        selectYear = leapYear;
      }

	 queryDate = firstDay.getFullYear()+""+
	 addZero(firstDay.getMonth()+1)+""+   //getMonth는 0,1,2,3,4식으로 가기 때문에 +1을 해주어야 한다
	 addZero(firstDay.getDate()); //addZero(함수) - 10보다 작은 것에 0을 붙이기 위해 만들어 둔 것 
	 
	 $("#pickedDay").text(dayList[now.getDay()]); // 요일 now. - 오늘을 기준
	 $("#pickedDate").text( addZero(now.getDate())); // 날짜
	 
      for (let i = 0; i < 42; i++) {
        if (i < firstDay.getDay()) {
          //비워두기
          output += `<li class="blank"><span></span></li>`;
          //continue;
        } else {
          if (now.getDate() === count && now.getFullYear() === firstDay.getFullYear() && now.getMonth() === firstDay.getMonth()) {
            output += `<li class="today" data-date="${count}" data-year="${firstDay.getFullYear()}" data-month="${firstDay.getMonth() + 1}"><span>${count}</span></li>`;
          } else {
            output += `<li data-date="${count}" data-year="${firstDay.getFullYear()}" data-month="${firstDay.getMonth() + 1}"><span>${count}</span></li>`;
          }
          count += 1;
        }
        if (count > selectYear[firstDay.getMonth()]) {
          break; // 반목문이 break를 만나면 종료
        }
      }
      dateUL.html( output ); // jquery에서 함수로 적용되어 이렇게 사용하여야한다
      gsap.from(".calendar .dates li", { scale: 0, ease: "power3", stagger: 0.02 });
      const dateLI = $(".calendar .dates li");
      let selectedDate;
      /*
      JAVASCRIPT방식
      dateLI.forEach(function (item, idx) {
        item.addEventListener("click", function () {
          //console.log(item.dataset.date);
          const selectDay = `${item.dataset.year}${addZero(item.dataset.month)}${addZero(parseInt(item.dataset.date))}`;
          console.log(selectDay);
          if (selectedDate) {
            selectedDate.classList.remove("on");
          }
          selectedDate = item;
          selectedDate.classList.add("on");
          queryDate = selectDay; // 이러면 값이 들어간다
        });
      });
      */
    }
    //이벤트 위임....(다음 달로 넘어가면 새로 리셋이 되기 때문에 해주어야 한다)
    //$("body").on("click",)
    
    $("body").on("click",".calendar .dates li",function() {// 늘 존재하는 body에 .calendar .dates li을 걸어준다
		const selectDay = $(this).data("year")+ addZero( $(this).data("month") )+addZero( $(this).data("date") );
        console.log(selectDay);
        queryDate = selectDay; // queryDate는 데이터 넘겨줄때 필요한 것
        $(this).addClass("on").siblings().removeClass("on"); /// 색깔 바뀌는 것 클릭시 on을 제거해라
        const clickNow = new Date($(this).data("year"),parseInt($(this).data("month"))-1,$(this).data("date")); // 클릭한 날짜를 기준으로 date객체 생성
        console.log(clickNow); 
        console.log(clickNow.getDay()); // 요일
        $("#pickedDate").text( addZero( clickNow.getDate() ));// 클릭시 text를 클릭한 getdate로 바꿔주기
        $("#pickedDay").text(dayList[clickNow.getDay()]); // 클릭시 text를 클릭한 getDay로 변경
        //아래로 리스트를 구해주는 코드
        const sendData = {
			pickedDate:queryDate // 1)넘겨주는 데이터가 쿼리 데이터로 넘어간다(json으로 받았을 것)->Todocontroller
		}
		$.ajax({
			url:"List.do", //todocontroller에서 넘겨준 것 기록
			data:JSON.stringify(sendData),// 문자화 하여 보내기(날리겠다)
			contentType:"application/json", //json타입으로(날리겠다)
			dataType:"json",// 받을땐 json을 기대한다
			method:"POST",// 메서드는 post방식
			success:function(res){
				$(".todoList").html("");
				$.each(res.todoList,function(idx,item){
					$(".todoList").append(`<li data-no="${item.no}" class="${item.done}">
						<span class="txt">${item.todo}</span>
						<button class="btnDelete"><span class="material-icons">delete</span></button>
					</li>`)
				});
			},
			error:function(err) {
				console.log(err);
			}
		})
	});
    function addZero(num) {
      if (num < 10) {
        return "0" + num;
      } else {
        return "" + num;
      }
    }
    
    $("body").on("click",".todoList li .btnDelete",function() {//btnDelete를 눌렀을때
		console.log("쓰레기통 클릭");
		//1 sql작성
		console.log($(this).parent().data("no"));
		const clickItem = $(this).parent(); //  li를 지워서 사용하기 위해 
		const sendData = {//보낼 데이터
			no:$(this).parent().data("no")
		}
		$.ajax({//데이터 정보 보내주기
			url:"Delete.do",
			data:JSON.stringify(sendData),
			contentType:"application/json", // 보내는 데이터의 type
			dataType:"json", // 받을때
			method:"POST",
			success:function(res) {
				console.log(res);  //{result:1} 또는 0이 넘어올 것이다
				if(res.result>0) {
					//clickItem.remove();
					gsap.to(clickItem,{x:-500,ease:"power3",onComplete:function(){
						clickItem.remove();
					}});
				}
			},
			error:function(err){
				console.log(err);
			}
		})
	});
	
	
	//done none 설정
	$("body").on("click",".todoList li .txt",function() {
		//console.log($(this).parent().data("no"));
		console.log($(this).parent().data("no"));
		const clickItem = $(this).parent();
		let state="";
		if(clickItem.hasClass("done")) {
			state="none"; // done을 가지고 있으면 state를 none으로 바꾸겠다
		} else {
			state="done";
		}
		console.log(state);
		const sendData = {
			no:$(this).parent().data("no"),
			done:state // done에는 state를 던져주기
		}
		$.ajax({
			url:"Update.do",
			data:JSON.stringify(sendData),
			contentType:"application/json", // 보내는 데이터의 type
			dataType:"json", // 받을때
			method:"POST",
			success:function(res) {
				if(res.result > 0) {
					if(clickItem.hasClass("none")) {
						clickItem.removeClass().addClass("done");
					} else {
						clickItem.removeClass().addClass("none");
					}
				}								
			},
			error:function(err){
				console.log(err);
			}
		})
	});
	
	$(".todo .btnAdd").on("click",function(){
		if($.trim( $("#todoTxt").val() )==="") {//trim(공백을 잘라준다)- 공백일 경우 
			alert("할일을 입력해 주세요.");
			$("#todoTxt").val("");
			$("#todoTxt").focus();
			return false;
		}
		
		const sendData = {// 아래 데이터 넘길떄 쓸 것
			todo:$("#todoTxt").val(),// index에 있는 .todo -> input(id) - todoTxt의 값을 받기
			done:"none", // 아직 완료한게 없기 때문에 none으로 임시
			pickedDate:queryDate
		}
		$.ajax({
			url:"Insert.do",// 로 가기 거기서 json을 리턴해준다
			data:JSON.stringify(sendData),// 문자화해서 보내는 방법
			contentType:"application/json", // 보내는 데이터의 type
			dataType:"json", // 받을때 json타입으로 받는다
			method:"POST",
			success:function(res) {
				console.log(res); //- 데이터를 받아서 넘어오는 것
				$(".todoList").html(""); // 중복 제거 위해 지워주기
				$.each(res.todoList,function(idx,item){//res.todoList(배열) - 키 값으로 넘겨온 것(배열로 떨구는 것 / res만 입력하면 바로 답을 떨구는 법 )
					$(".todoList").append(`<li data-no="${item.no}" class="${item.done}">
						<span class="txt">${item.todo}</span>
						<button class="btnDelete"><span class="material-icons">delete</span></button>
					</li>`) // todoList 클래스에 넣어주기
				});
				$("#todoTxt").val("");// 데이터가 잘 떨궈 졌을때 공백으로 바꿔주게 만들때
				$("#todoTxt").focus();// 그 뒤 포커스 맞춰주기
				
			},
			error:function(err){
				console.log(err);
			}
		})
	});
    
    
    
    
    