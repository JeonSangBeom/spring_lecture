
let selectedItem = null;
let grid = null;
function makeList(_list) {
	const list = _list;
    let output = "";
    $.each(list, function (idx, item) {
      console.log(item);
      const categories = item.category.split(",").join(" ");
      //console.log(categories);
      output += `
        <li class="item ${categories}" data-no="${item.no}">
          <a href="">
            <div class="imgBox">
              <img src="${item.img}" alt="" />
            </div>
            <div class="info">
              <h2>${item.title}</h2>
              <p class="desc">${item.contents}</p>
            </div>
            <span class="replyCount">${item.replyCount}</span>
          </a>
        </li>
      `;
    });
    $("#list > ul").html(output);
    //Fancybox.bind("[data-fancybox]");
	
	if(grid!=null){
		grid.isotope("destroy");
	}
    $("#list ul").imagesLoaded(function () {
      grid = $("#list ul").isotope({
        itemSelector: ".item",
        layoutMode: "masonry",
      });
      $("#filter li").on("click", function () {
        $(this).addClass("on").siblings("li").removeClass("on");
        const filtering = $(this).data("filter");
        console.log(filtering);
        grid.isotope({
          filter: "." + filtering,
        });
      });
    });
}

$.ajax({
  url: "JsonList02.do",
  success: function (res) {
    makeList(res.galleryList);
  },
});

let boardId;
// dom 생성전에 이벤트 걸때는 body에 걸고 매개변수로 링크를 넘겨 준다.
$("body").on("click", "#list li", function () {
  $("body").addClass("overHidden");
  $("html,body").scrollTop(0);
  $("#detail").show();
  const imgSrc = $(this).find(".imgBox img").attr("src");
  $("#detail .imgBox img").attr("src", imgSrc);
  boardId = $(this).data("no");
  console.log(boardId);
  selectedItem = $(this);
  gsap.to("#detail", {
    top: 0,
    ease: "bounce",
    duration: 1.5,
  });
  const sendData = {
    boardId: boardId,
  };
  $.ajax({
    url: "ReplyList.do",
    data: sendData,
    type: "POST",
    success: function (res) {
      console.log(res);
      makReplyList(res.replyList);
    },
  });

  return false;
});

$("body").on("click", ".replyList li button", function () {
  const sendData = {
    no: $(this).parent().data("no"),
    boardId: boardId,
  };
  console.log(sendData);
  $.ajax({
    url: "DeleteReply.do",
    data: sendData,
    type: "POST",
    success: function (res) {
      console.log(res);
      if (res.result > 0) {
        makReplyList(res.replyList);
      } else {
        alert("시스템 오류입니다. 잠시후 다시 시도해주세요");
      }
      selectedItem.find(".replyCount").text(res.replyList.length);
    },
  });
});

$("#detail .btnClose").on("click", function () {
  gsap.to("#detail", {
    top: "-100%",
    ease: "back.in",
    duration: 1,
    onComplete: function () {
      $("#detail").hide();
      $("body").removeClass("overHidden");
    },
  });
});

$("#detail .btnReply").on("click", function () {
  const reply = $("#detail textarea").val();
  const sendData = {
    boardId: boardId,
    reply: reply,
  };
  $.ajax({
    url: "InsertReply.do",
    type: "POST",
    data: sendData,
    success: function (res) {
      console.log(res);
      makReplyList(res.replyList);
      selectedItem.find(".replyCount").text(res.replyList.length);
    },
  });
  //console.log(reply);
});

function makReplyList(_list) {
  const list = $("#detail .replyList .list");
  const replyList = _list;
  let output = "";
  $("#detail textarea").val("");
  list.html("");
  $.each(replyList, function (idx, item) {
    output += `
          <li data-no="${item.no}" data-boardid="${item.boardId}">
            <div class="txt">${item.reply}</div>
            <button><span class="material-icons">delete</span></button>
          </li>
        `;
  });
  list.append(output);
}

$("#reply").on("keydown", function (e) {
  const contents = $(this).val();
  if (contents.length > 10) {
    alert("100글자 까지만 입력 가능합니다.");
    $(this).val(contents.substr(0, 10));
    return;
  }
  $(".txtCount .count").text(contents.length);
});



$("#detail .btnClose02").on("click",function(){
	gsap.to("#detail",{top:"-100%",ease:"back.in",duration:1});
});
$("#detail .btnDelete").on("click",function(){
	//ajax 데이터 보내고 결과를 받아서 제대로 지워졌으면 위로 보내버리면 된다.
	const no = selectedItem.data("no");
	const sendData = {
		no:no
	}
	$.ajax({
		url:"DeleteGallery.do",
		data:sendData,
		type:"POST",
		success:function(res) {
			console.log(res);
			gsap.to("#detail",{top:"-100%",ease:"back.in",duration:1});
			makeList(res.galleryList);
		},
		error:function(err) {
			alert(err);
		}
	})
	//console.log(no);
});


console.log($(".fileBox input[type='file']"));

$(".fileBox input[type='file']").on("change",function(){
	const fileName = $(this).val(); // file에 있는 내용을 복사해서
	console.log(fileName); 
	$(this).parent().siblings(".fakeFile").val(fileName);
});






