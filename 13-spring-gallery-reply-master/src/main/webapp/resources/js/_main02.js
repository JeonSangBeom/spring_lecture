let selectedItem = null;

$.ajax({
  url: "JsonList02.do",
  success: function (res) {
    //console.log(res);
    makeList(res.galleryList);
    $("#filter li").on("click", function () {
      $(this).addClass("on").siblings().removeClass("on");
      const flitering = $(this).data("filter");
      grid.isotope({
        filter: "." + flitering, // 클래스로 정렬하기 때문에 . 붙여줘야함
        sortBy: "point",
        sortAscending: false,
      });
    });
  },
});

let boardId;
// dom 생성 전 이벤트 걸 때는 body에 걸어서 링크를 넘겨줌
$("body").on("click", "#list li", function () {
  $("body").addClass("overHidden");
  $("html, body").scrollTop(0); // 맨 위로 보내기
  $("#detail").show();
  const imgSrc = $(this).find(".imgBox img").attr("src"); // imgSrc를 구해옴
  $("#detail .imgBox img").attr("src", imgSrc); // imgSrc를 입력
  boardId = $(this).data("no");
  selectedItem = $(this);
  //console.log(boardId);
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
    type: "post",
    success: function (res) {
      //console.log(res);
      makeReplyList(res.replyList);
    },
  });
  return false; // a태그때문에..
});

$("#reply").on("keydown", function (e) {
  const contents = $(this).val();
  if (contents.length > 100) {
    alert("100자를 초과할 수 없습니다.");
    $(this).val(contents.substr(0, 100));
    return;
  }
  $(".txtCount .count").text(contents.length);
});

$("#detail .btnReply").on("click", function () {
  const reply = $("#detail textarea").val();
  const sendData = {
    boardId: boardId,
    reply: reply,
  };
  $.ajax({
    url: "InsertReply.do",
    type: "post",
    data: sendData,
    success: function (res) {
      //console.log(res);
      makeReplyList(res.replyList);
      selectedItem.find(".replyCount").text(res.replyList.length);
    },
  });
  $(".txtCount .count").text(0);
});

$("#detail .btnClose").on("click", function () {
  close();
});

$("body").on("click", ".list li button", function () {
  console.log($(this).parent().data("no"));
  const sendData = {
    no: $(this).parent().data("no"),
    boardId: boardId,
  };
  $.ajax({
    url: "DeleteReply.do",
    data: sendData,
    type: "post",
    success: function (res) {
      if (res.result > 0) {
        makeReplyList(res.replyList);
      } else {
        alert("시스템 오류입니다. 잠시후 다시 시도하세요");
      }
      selectedItem.find(".replyCount").text(res.replyList.length);
    },
  });
});

$("#detail .btns .btnReplyDelete").on("click", function () {
  if (selectedItem.find(".replyCount").text() == "0") return;
  const sendData = {
    boardId: boardId,
  };
  $.ajax({
    url: "DeleteAllReply.do",
    data: sendData,
    success: function (res) {
      if (res.result > 0) {
        makeReplyList(res.replyList);
      } else {
        alert("시스템 오류입니다.");
      }
      selectedItem.find(".replyCount").text(0);
    },
  });
});

$("#detail .btns .btnClose02").on("click", function () {
  close();
});

$("#detail .btns .btnDelete").on("click", function () {
  //console.log("글삭제");
  const sendData = {
    no: selectedItem.data("no"),
  };
  $.ajax({
    url: "Delete.do",
    data: sendData,
    success: function (res) {
      //console.log(res);
      makeList(res.galleryList);
      console.log("res.result==" + res.result);
      if (res.result > 0) {
        close();
      } else {
        alert("시스템오류입니다.");
      }
    },
  });
});

function makeReplyList(_list) {
  const list = $("#detail .replyList .list");
  const replyList = _list;
  let output = "";
  $("#detail textarea").val("");
  list.html("");
  $.each(replyList, function (idx, item) {
    output += `
          <li data-no=${item.no} data-boardid="${item.boardId}">
            <div class="txt">${item.reply}</div>
            <button><span class="material-icons">delete</span></button>
          </li>
        `;
  });
  list.append(output);
}

function makeList(_list) {
  let output = "";
  const list = _list;
  $.each(list, function (idx, item) {
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
              <p class="point"><span>${item.point}</span></p>
            </div>
            <span class="replyCount">${item.replyCount}</span>
          <a>
        </li>
      `;
  });
  $("#list > ul").html(output);
  let grid = null;
  $("#list ul").imagesLoaded(function () {
    grid = $("#list ul").isotope({
      itemSelector: ".item",
      layoutMode: "masonry",
      getSortData: {
        point: ".point",
      },
    });
  });
}

function close() {
  gsap.to("#detail", {
    top: "-100%",
    ease: "back.in",
    duration: 1,
    onComplete: function () {
      $("body").removeClass("overHidden");
      $("#detail").hide();
    },
  });
}