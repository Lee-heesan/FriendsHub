document.addEventListener("DOMContentLoaded", function() {
              document.getElementById("newForm").addEventListener("click", function() {
                // 페이지 이동을 처리할 코드
                window.location.href = "/notice/new"; // 여기에 새로운 페이지의 URL을 입력하세요.
              });
            });

        $(document).ready(function(){
            $("#searchBtn").on("click",function(e) {
                e.preventDefault();
                page(0);
            });
        });

        function page(page){
            var searchDateType = $("#searchDateType").val();
            var searchBy = $("#searchBy").val();
            var searchQuery = $("#searchQuery").val();

            location.href="/notices/" + page + "?searchDateType=" + searchDateType
            + "&searchBy=" + searchBy
            + "&searchQuery=" + searchQuery;
        }