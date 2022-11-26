$.ajax({
  url: "JsonList.do", // 여기로 가고
  success: function (res) {
    console.log(res);
    const list = res.galleryList;// galleryList를 배열로 던져주기
    let output = "";
    $.each(list, function (idx, item) {
      console.log(item);
      const categories = item.category.split(",").join(" ");
      //split - 문자열을 배열로 쪼개 준다  join - 문자열로 바꿔준다
      //console.log(categories);
      output += `
        <li class="item ${categories}" data-no="${item.no}">
          <a href="${item.img}" data-caption="${item.title}" data-fancybox="${categories}">
            <div class="imgBox">
              <img src="${item.img}" alt="" />
            </div>
            <div class="info">
              <h2>${item.title}</h2>
              <p class="desc">${item.contents}</p>
            </div>
          </a>
        </li>
      `;
    });
    $("#list > ul").html(output); // 이곳에 넣어주기 (output설정 한 것을)
    Fancybox.bind("[data-fancybox]");//data-fancybox 가 들어가 있는 곳에 Fancybox를 적용해라
    let grid = null;
    $("#list ul").imagesLoaded(function () {
      grid = $("#list ul").isotope({
        itemSelector: ".item",
        layoutMode: "masonry",
      });

      $("#filter li").on("click", function () {
        $(this).addClass("on").siblings("li").removeClass("on");// on제거
        const filtering = $(this).data("filter");
        console.log(filtering);
        grid.isotope({ // 정렬
          filter: "." + filtering,// 클래스로 정렬을 한 것 이라 앞에 .을 붙여 줘야 한다
        });
      });
    });
  },
});