const dateUL = $(".calendar .dates ul");

const dayList = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];

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

let queryDate = "";

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

queryDate = now.getFullYear() + addZero(now.getMonth() + 1) + addZero(now.getDate());

//inputDate.value = `${now.getFullYear()} / ${addZero(now.getMonth() + 1)} / ${addZero(now.getDate())}`;
function makeCalendar(pYear, pMonth) {
  //윤년 공식 4로 떨어지면 윤년,100년 단위는 윤년 아님, 400으로 떨어지면 윤년
  let output = "";
  let count = 1;
  firstDay = new Date(pYear, pMonth, 1);
  txtYear.text(firstDay.getFullYear());
  txtMonth.text(addZero(firstDay.getMonth() + 1));
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

  queryDate = firstDay.getFullYear() + "" + addZero(firstDay.getMonth() + 1) + "" + addZero(firstDay.getDate());

  $("#pickedDay").text(dayList[now.getDay()]);
  $("#pickedDate").text(addZero(now.getDate()));

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
  dateUL.html(output);
  gsap.from(".calendar .dates li", { scale: 0, ease: "power3", stagger: 0.02 });
  const dateLI = $(".calendar .dates li");
  let selectedDate;
  /*
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
		queryDate = selectDay;
	  });
	});
	*/
}
//이벤트 위임....
$("body").on("click", ".calendar .dates li", function () { // 달력 클릭시
  showCorona($(this).data("year") + addZero($(this).data("month")) + addZero($(this).data("date"))); // 클릭시 매개변수 맞춰서 써주기
  $(this).addClass("on").siblings().removeClass("on");//siblings 이웃들 전부 on 지우기
  $(".calendarBox").stop().slideUp(250);// 내려온게 다시 안올라게 해주기
  $(".inputBox input").val($(this).data("year") + "/" + addZero($(this).data("month")) + "/" + addZero($(this).data("date")));// 달ㄹ력 날짜 클릭시 날짜 표시
  //$(".calendarBox").stop().fadeOut(250);
});

function addZero(num) {
  if (num < 10) {
    return "0" + num;
  } else {
    return "" + num;
  }
}

showCorona(now.getFullYear() + addZero(now.getMonth() + 1) + addZero(now.getDate())); // 시작하자마자 있어야 하기 때문에 생성

let myChart = null; // 선언 해두기 - 전역변수로
function showCorona(_date) { // 달력 클릭시 날려야 하기 때문에 함수로 묶어 사용
  const sendData = {
    date: _date, // 매개변수로 전달되는 값을 던져준 것
  };
  $.ajax({
    url: "Corona.do",
    dataType: "json",
    data: sendData,
    success: function (res) {
      console.log(res);
      console.log(res.response.body.items.item);
      const cities = []; // 도시로 쓸 배열 생성
      const incDecArray = [];
      $.each(res.response.body.items.item, function (idx, item) {
        cities.push(item.gubun);
        incDecArray.push(item.incDec);// 배열 안에 넣어 두기
      });
      const data = {
        labels: cities, // 도시 이름
        datasets: [
          {
            label: "CORONA DATA", // 제목
            backgroundColor: [// 색깔
              "#053C5E",
              "#1D3958",
              "#A4133C",
              "#C9184A",
              "#FF4D6D",
              "#FF758F",
              "#FF8FA3",
              "#FFB3C1",
              "#590D22",
              "#800F2F",
              "#A4133C",
              "#C9184A",
              "#FF4D6D",
              "#FF758F",
              "#FF8FA3",
              "#FFB3C1",
              "#590D22",
              "#800F2F",
              "#A4133C",
            ],
            borderColor: "rgb(255,255,255)",
            borderWidth: 4,
            data: incDecArray,
          },
        ],
      };

      const config = {
        type: "bar",// doughnut 방식도 가능
        data: data,
        options: {},
      };
      if (myChart !== null) { //myChart가 null 이 아니라면 ( 선언해둔 값이 있어 처음 것은 지워지지 않는다)
        myChart.destroy();// 있으면 destroy해줘라
      }
      myChart = new Chart(document.querySelector("#coronaChart"), {
        type: "bar",
        data: data,
      });
    },
    error: function (err) {
      console.log(err);
    },
  });
}

$(".calendarWrap .inputBox").on("click", function () {
  $(".calendarBox").stop().slideToggle(250); // fadeToggle 도 있다 둘다 나타났다 사라지게 하는 역할
});			