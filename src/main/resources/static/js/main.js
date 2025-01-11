    $(document).ready(function(){
      var btn = $("#buttonList li");
      var current=0; //현재 화면에 보이는 슬라이드의 인덱스
      var setIntervalId;

      btn.click(function(){
          var i = $(this).index();

          btn.removeClass("act");
          btn.eq(i).addClass("act");

          move(i);
      });

      //자동실행 제어

      $(".carousel-inner").on({
            mouseover: function() {
                clearInterval(setIntervalId);
            },
            mouseout: function() {
                timer();
            }
        });


      //자동 배너 넘김
      timer();
      function timer(){
         setIntervalId =setInterval(function(){
           var n= current + 1 ;
           if(n==4){
                n=0;
           }
           move(n);
           btn.removeClass("act");
           btn.eq(n).addClass("act");
         },3000);
      }


      function move(n){
        if(n==current) return; //현재 슬라이드 인덱스 값과 다음에 오는 슬라이드의 인덱스값을 비교 같은 실행 리턴

         var currentEl = $("#brandVisual ul li").eq(current); //현재 화면에 보이는 슬라이드
         var nextEl = $("#brandVisual ul li").eq(n); //버튼에 의해 보이는 슬라이드

         currentEl.stop().css({left:"0%"}).animate({left:"-100%"});
         nextEl.stop().css({left:"100%"}).animate({left:"0%"});
         current = n;
      }

        $(".page_info_content1 ul li").on("mouseover", function () {
          $(".page_info_content1 ul li").css("transform", "scale(1.1)");
          $(".page_info_content1 ul li").not(this).css("transform", "scale(0.8)"); // 다른 요소를 축소
        });

        $(".page_info_content1 ul li").on("mouseout", function () {
            $(".page_info_content1 ul li").css("transform", "scale(1)"); // 마우스를 떼면 원래 크기로 복원
        });
    });
