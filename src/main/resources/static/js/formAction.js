// 「複製」を押下
function addButton() {
    const parent = document.getElementById("parent")
    let tr = document.getElementsByTagName("tr")
    let tr_clone = tr[tr.length - 1].cloneNode(true)
    parent.appendChild(tr_clone)
}

// 「一時保存」押下
function saveData() {
    let tr = document.getElementsByTagName("tr")
    alert(tr.length - 1 + "件一時保存しました！")
    // Json形式にして「data"i"」キー名で保存
    for (let i = 0; i < tr.length; i++) {
        let data = [
            document.getElementsByName("department")[i].value,
            document.getElementsByName("employeeId")[i].value,
            document.getElementsByName("area")[i].value,
            document.getElementsByName("sales")[i].value,
            document.getElementsByName("customers")[i].value,
            document.getElementsByName("updateDate")[i].value,
            i
        ]
        localStorage.setItem("data" + i, JSON.stringify(data));
    }
}

// 画面読み込み時、ローカルストレージにデータがあれば、表示
window.onload = function () {
    // ローカルストレージから取得したデータを配列(data)に格納
    if (localStorage.length) {
        let data = new Array(localStorage.length)
        for (let i = 0; i < data.length; i++) {
            let jsonData = JSON.parse(localStorage.getItem(localStorage.key(i)))
            data[i] = new Array(jsonData.length)
            for (let j = 0; j < jsonData.length; j++) {
                data[i][j] = jsonData[j]
                console.log(jsonData[6]);
            }
        }
        // 配列(data)のデータの数だけ「tr」タグを生成後、
        // 配列(data)から取得した値を各nameにセット
        for (let i = 1; i < data.length; i++) {
            const parent = document.getElementById("parent");
            let tr = document.getElementsByTagName("tr")
            let tr_clone = tr[i].cloneNode(true)
            parent.appendChild(tr_clone)
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
const submit = document.getElementById("submit")
submit.addEventListener("submit", function () {
    localStorage.clear()
})

// 「一時保存取り消し」が押下されたらローカルストレージをクリア
function cancel() {
    alert("取り消しました！")
    localStorage.clear()
    location.reload();
}

// 「削除」を押下されたら該当の行を削除
function deleteTr(obj) {
    const defaultRow = document.getElementsByTagName("tr")
    let tr = obj.parentNode.parentNode
    let storage = "data" + tr.sectionRowIndex
    for (let i = 0; i < localStorage.length; i++) {
        if (localStorage.key(i) == storage) {
            localStorage.removeItem(storage)
        }
    }
    if (defaultRow.length > 2) {
        tr.parentNode.deleteRow(tr.sectionRowIndex)
    }
}

// ブラウザバック強制リロード
window.addEventListener('pageshow', () => {
    if (window.performance.navigation.type == 2) location.reload();
});