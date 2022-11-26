let selectedItem = null; // 클릭한 것을 잡아두기 위해 미리 선언
let grid = null;
function makeList(_list) { // 반복되는 함수로 
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

  if (grid != null) {
    grid.isotope("destroy");// 메소드를 완전이 없애는 방법 아래 isotope 실행시 에러 발생 방지를 위해 
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
    makeList(res.galleryList);//반복되는 함수
  },
});

let boardId; // boardId를 하나 만들어 두고 실행
// 클릭시댓글창 생성
// dom 생성전에 이벤트 걸때는 body에 걸고 매개변수로 링크를 넘겨 준다.
$("body").on("click", "#list li", function () {
  $("body").addClass("overHidden");
  $("html,body").scrollTop(0); // 스크롤이 제일 위 고정하여 생성 , 사용안할시 하단에서 클릭하면 위치가 이상하게 뜬다
  $("#detail").show(); // 나타내게 하기(display를 block로 바꿔 주는 것)
  const imgSrc = $(this).find(".imgBox img").attr("src"); // 이미지 경로의 속성
  $("#detail .imgBox img").attr("src", imgSrc); // 이미지 불러오기 src속성에 imgSrc를 넣겠다(src 하나만 쓰면 값을 구해오는게 된다)
  boardId = $(this).data("no"); // boardid값을 no로 해서 넘기기
  console.log(boardId);
  selectedItem = $(this); // 클릭한 나 자신
  gsap.to("#detail", {
    // 애니메이션 효과를 주기 위해  / top -1000 으로 지정해둔 상태(scss)
    top: 0,
    ease: "bounce",
    duration: 1.5,
  });
  const sendData = {
    boardId: boardId,
  };
  $.ajax({
    // 클릭 후 댓글(입력해둔 것) 보이게 설정 하기 위해
    url: "ReplyList.do",
    data: sendData,
    type: "POST",
    success: function (res) {
      console.log(res);
      makReplyList(res.replyList);
    },
  });

  return false; // a 태그가 있을 경우 return false 사용
});

// 클릭시 지워주기 (삭제통 버튼)
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
      // selectedItem 안에 있는 replyCount(동그라미 안에 글자 들어간 것)를 찾아서 TEXT로 갯수 넣어주기
    },
  });
});

// x 클릭시 댓글창 사라지게 만들기
$("#detail .btnClose").on("click", function () {
  gsap.to("#detail", {
    //올라갈떄 사용
    top: "-100%",
    ease: "back.in",
    duration: 1,
    onComplete: function () {
      // 올라가는게 끝나면
      $("#detail").hide(); //가려주는것 (display - hidden시켜 준다)
      $("body").removeClass("overHidden");
    },
  });
});

// 댓글 입력시 작동
$("#detail .btnReply").on("click", function () {
  const reply = $("#detail textarea").val(); // 댓글 내용(입력한 내용 값)
  const sendData = {
    boardId: boardId, // 위에 no로 받게 입력해 둔 것
    reply: reply, // const로 입력한 것
  };
  $.ajax({
    url: "InsertReply.do", // 여기로 보내기
    type: "POST",
    data: sendData,
    success: function (res) {
      console.log(res);
      makReplyList(res.replyList); // 매개변수로 던져주기
      selectedItem.find(".replyCount").text(res.replyList.length);
    },
  });
  //console.log(reply);
});

function makReplyList(_list) {
  // 중복하여 사용하기 때문에 사용 / 기존 res가 없기 때문에 매개변수 사용하여 이용
  const list = $("#detail .replyList .list");
  const replyList = _list; // 매개변수로 사용된 것
  let output = ""; // 성공시 공백을 쳐두기
  $("#detail textarea").val("");
  list.html(""); // 공백 처리 후 반복문 돌리기 위해 미리 공백들 입력
  $.each(replyList, function (idx, item) {
    //each 반복문
    output += `
          <li data-no="${item.no}" data-boardid="${item.boardId}">
            <div class="txt">${item.reply}</div>
            <button><span class="material-icons">delete</span></button>
          </li>
        `;
  });
  list.append(output); // each후 추가를 해주어야 작동(list가 ul태그)
}

// 글자 수 제한 주는 법
$("#reply").on("keydown", function (e) {
  const contents = $(this).val(); // 입력 값 받아오기
  if (contents.length > 10) {
    alert("100글자 까지만 입력 가능합니다.");
    $(this).val(contents.substr(0, 10)); // 넘는 글자를 잘라준다
    return;
  }
  $(".txtCount .count").text(contents.length); // 글자 입력만큼 갯수 증가
});

$("#detail .btnClose02").on("click", function () {
  gsap.to("#detail", { top: "-100%", ease: "back.in", duration: 1 });
});

$("#detail .btnDelete").on("click", function () {
  //ajax 데이터 보내고 결과를 받아서 제대로 지워졌으면 위로 보내버리면 된다.
  const no = selectedItem.data("no"); // data-no 값 / selectedItem - 전역변수로 설정해둔 값
  const sendData = {
    no: no,
  };
  $.ajax({
    url: "DeleteGallery.do", // controller에 설정한 위치
    data: sendData,
    type: "POST",
    success: function (res) {//res리턴 
      console.log(res);
      gsap.to("#detail", { top: "-100%", ease: "back.in", duration: 1 });//삭제후 새로운 창 위로 올려 사라지게 해주기
      makeList(res.galleryList);
    },
    error: function (err) {
      alert(err);
    },
  });
  //console.log(no);
});

console.log($(".fileBox input[type='file']"));

$(".fileBox input[type='file']").on("change", function () { // 파일이 바뀌면 / (업로드 위치)내용이 안뜨지만 편법으로 사용하는 방법
  const fileName = $(this).val(); // file에 있는 내용을 복사하기 위해 변수 지정
  console.log(fileName);
  $(this).parent().siblings(".fakeFile").val(fileName);
});

