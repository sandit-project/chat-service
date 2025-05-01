// URL에서 roomId를 쿼리 파라미터로 받기
const roomId = new URLSearchParams(location.search).get("roomId");
if (!roomId) {
    alert("Room ID가 필요합니다.");
}

const socket = new SockJS("/chat");
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
    stompClient.subscribe(`/topic/room/${roomId}`, (msg) => {
        const message = JSON.parse(msg.body);
        $('#chatBox').append(`<p><b>${message.sender}</b>: ${message.message}</p>`);
        $('#chatBox').scrollTop($('#chatBox')[0].scrollHeight);  // 스크롤 아래로 이동
    });
});

function sendMessage() {
    const message = {
        roomId: roomId,
        sender: "익명",
        message: $('#messageInput').val()
    };
    stompClient.send("/app/chat.send", {}, JSON.stringify(message));
    $('#messageInput').val('');
}

$(document).ready(function () {
    $('#sendBtn').on('click', function () {
        sendMessage();
    });
    $('#messageInput').on('keypress', function (e) {
        if (e.which === 13) {
            sendMessage();
        }
    });
});
