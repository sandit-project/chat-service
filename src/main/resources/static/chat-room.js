// ✅ 랜덤 닉네임 생성 또는 localStorage에서 가져오기
function getOrCreateNickname() {
    let nickname = localStorage.getItem('nickname');
    if (!nickname) {
        nickname = "User" + Math.floor(Math.random() * 1000);
        localStorage.setItem('nickname', nickname);
    }
    return nickname;
}

// ✅ URL에서 roomId 받기 + 닉네임 자동 설정
const urlParams = new URLSearchParams(location.search);
const roomId = urlParams.get("roomId");
const nickname = getOrCreateNickname();

if (!roomId) {
    alert("Room ID가 필요합니다. 예: ?roomId=123");
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("userInfo").innerText = `당신의 닉네임: ${nickname}`;
});

const socket = new SockJS("/chat");
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
    stompClient.subscribe(`/topic/room/${roomId}`, (msg) => {
        const message = JSON.parse(msg.body);
        const isMine = message.sender === nickname;

        const messageClass = isMine ? "my-message" : "other-message";
        const messageHtml = `<div class="${messageClass}">
            <b>${message.sender}</b>: ${message.message}
        </div>`;

        $('#chatBox').append(messageHtml);
        $('#chatBox').scrollTop($('#chatBox')[0].scrollHeight);
    });
});

function sendMessage() {
    const message = {
        roomId: roomId,
        sender: nickname,
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
