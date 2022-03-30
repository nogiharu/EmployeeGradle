// 「複製」を押下
function addButton() {
    counter()
    const parent = document.getElementById("parent")
    let tr = document.getElementsByTagName("tr")
    let tr_clone = tr[tr.length - 1].cloneNode(true)
    tr_clone.id = "data" + localStorage.getItem("count")
    parent.appendChild(tr_clone)
}
function counter() {
    if (!localStorage.getItem("count")) {
        localStorage.setItem("count", 1)
    } else {
        let count = localStorage.getItem("count")
        localStorage.setItem("count", JSON.stringify(++count))
        JSON.stringify
    }
}


// 「一時保存」押下
function saveData() {

    let tr = document.getElementsByTagName("tr")
    alert(tr.length - 1 + "件一時保存しました！")
    for (let i = 0; i < tr.length; i++) {
        let key = document.querySelectorAll("[id^=data]")
        //let data = []
        //for (let j = 0; j < key[i].children.length-1; j++) {
        //    data = [key[i].children[j].children[0].value,]}
        let data = [
            key[i].children[0].children[0].value,
            key[i].children[1].children[0].value,
            key[i].children[2].children[0].value,
            key[i].children[3].children[0].value,
            key[i].children[4].children[0].value,
            key[i].children[5].children[0].value
        ]
        localStorage.setItem(key[i].getAttribute("id"), JSON.stringify(data));
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
            for (let j = 0; j < data[i].length; j++) {
                data[i][j] = jsonData[j]
            }
        }
        const newData = data.filter(e => (e.length > 2))
        // 配列(data)のデータの数だけ「tr」タグを生成後、
        // 配列(data)から取得した値を各nameにセット
        for (let i = 0; i < newData.length; i++) {
            const parent = document.getElementById("parent");
            let tr = document.getElementsByTagName("tr")
            let tr_clone = tr[1].cloneNode(true)
            if (localStorage.key(i) != "count") {
                tr_clone.id = localStorage.key(i)
                parent.appendChild(tr_clone)
            }
            let key = document.querySelectorAll("[id^=data]")
            for (let j = 0; j < newData[i].length; j++) {
                key[i].children[j].children[0].value = newData[i][j]
            }
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
    let count = JSON.parse(localStorage.getItem("count")) 
    for (let i = 0; i < count+1; i++) {
        localStorage.removeItem("data" + i)
    }
    location.reload();
}


// 「削除」を押下されたら該当の行を削除
function deleteTr(obj) {
    const defaultRow = document.getElementsByTagName("tr")
    let tr = obj.parentNode.parentNode
    if (tr.id != "data0") {
        localStorage.removeItem(tr.id)
        tr.parentNode.deleteRow(tr.sectionRowIndex)
    }
}

// ブラウザバック強制リロード
window.addEventListener('pageshow', () => {
    if (window.performance.navigation.type == 2) location.reload();
});