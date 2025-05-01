function fetchRooms() {
    $.ajax({
        url: '/api/chat/rooms',
        method: 'GET',
        success: function(data) {
            const roomListDiv = $('#roomList');
            roomListDiv.empty();

            data.forEach(room => {
                const roomDiv = $('<div></div>')
                    .text(room.name) // ID 제거
                    .click(() => {
                        window.location.href = '/chat-room?roomId=' + room.id;
                    });
                roomListDiv.append(roomDiv);
            });
        },
        error: function() {
            alert("채팅방 목록을 불러오는 데 실패했습니다.");
        }
    });
}

function createRoom() {
    const roomName = $('#roomName').val().trim();
    if (!roomName) {
        alert("방 이름을 입력하세요.");
        return;
    }

    $.ajax({
        url: '/api/chat/rooms',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ name: roomName }),
        success: function() {
            $('#roomName').val('');
            fetchRooms();
        },
        error: function(xhr) {
            if (xhr.status === 409) {
                alert("이미 존재하는 방 이름입니다.");
            } else {
                alert("방 생성 중 오류가 발생했습니다.");
            }
        }
    });
}

// 초기 로딩
$(document).ready(function() {
    fetchRooms();
});
