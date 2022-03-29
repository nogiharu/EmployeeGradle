// 「複製」を押下
function addButton() {
    const parent = document.getElementById("parent");
    let tr = document.getElementsByTagName("tr")
    let tr_clone = tr[tr.length - 1].cloneNode(true)
    parent.appendChild(tr_clone)
}

// 一時保存
function saveData() {
    let tr = document.getElementsByTagName("tr")
    alert(tr.length - 1 + "件一時保存しました！")
    let data = []
    // Json形式にして「data"i"」キー名で保存
    for (let i = 0; i < tr.length; i++) {
        let data = [
            document.getElementsByName("department")[i].value,
            document.getElementsByName("employeeId")[i].value,
            document.getElementsByName("area")[i].value,
            document.getElementsByName("sales")[i].value,
            document.getElementsByName("customers")[i].value,
            document.getElementsByName("updateDate")[i].value
        ]
        localStorage.setItem("data" + i, JSON.stringify(data))
    }
}

// 画面読み込み時、ローカルストレージにデータがあれば表示
window.onload = function () {
    if (localStorage.length) {
        let data = new Array(localStorage.length)
        for (let i = 0; i < data.length; i++) {
            let json = JSON.parse(localStorage.getItem("data" + i))
            data[i] = new Array(json.length)
            for (let j = 0; j < json.length; j++) {
                data[i][j] = json[j]
            }
        }
        // ローカルストレージのデータ-1の数だけ「tr」タグを生成
        for (let i = 1; i < data.length - 1; i++) {
            const parent = document.getElementById("parent");
            let tr = document.getElementsByTagName("tr")
            let tr_clone = tr[i].cloneNode(true)
            parent.appendChild(tr_clone)
        }
        // 
        for (let i = 0; i < data.length; i++) {
            document.getElementsByName("department")[i].value = data[i][0]
            document.getElementsByName("employeeId")[i].value = data[i][1]
            document.getElementsByName("area")[i].value = data[i][2]
            document.getElementsByName("sales")[i].value = data[i][3]
            document.getElementsByName("customers")[i].value = data[i][4]
            document.getElementsByName("updateDate")[i].value = data[i][5]
        }
    }
}
// 「確定」が押下されたらローカルストレージをクリア
let submit = document.getElementById("submit")
submit.addEventListener("submit", function () {
    localStorage.clear()
})
// 「一時保存取り消し」が押下されたらローカルストレージをクリア
function cancel() {
    alert("取り消しました！")
    localStorage.clear()
    location.reload();
}
