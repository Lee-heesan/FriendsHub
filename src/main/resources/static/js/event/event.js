 function copyToClipboard() {
            var copyText = window.location.href;
            navigator.clipboard.writeText(copyText);
            alert("현재 페이지 링크가 복사되었습니다");
        }