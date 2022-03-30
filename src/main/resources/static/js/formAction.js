// 「複製」を押下
function addButton() {
    counter()
    const parent = document.getElementById("parent")
    let tr = document.getElementsByTagName("tr")
    let tr_clone = tr[tr.length - 1].cloneNode(true)
    tr_clone.id = "data" + localStorage.getItem("count")
    parent.appendChild(tr_clone)


    /*   var c = document.querySelectorAll("[id^=data]")
      for (let index = 0; index < c.length; index++) {
          //console.log(c[index].getAttribute("id"));
      }
      let hoge = document.getElementById("data0") */
}
function counter() {
    if (!localStorage.getItem("count")) {
        localStorage.setItem("count", 1)
    } else {
        let count = localStorage.getItem("count")
        localStorage.setItem("count", ++count)
    }
}


// 「一時保存」押下
function saveData() {



    let tr = document.getElementsByTagName("tr")

    alert(tr.length - 1 + "件一時保存しました！")
    for (let i = 0; i < tr.length; i++) {

        let key = document.querySelectorAll("[id^=data]")
        /* console.log(key[i].children[0].children[0].value);
        console.log(key[i].children[1].children[0].value);
        console.log(key[i].children[2].children[0].value);
        console.log(key[i].children[3].children[0].value);
        console.log(key[i].children[4].children[0].value);
        console.log(key[i].children[5].children[0].value); */
        console.log(key[i].length);
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
        let data = [localStorage.length -1]
        for (let i = 0; i < data.length; i++) {
    
            let jsonData = JSON.parse(localStorage.getItem(localStorage.key(i)))
            data[i] = new Array(jsonData.length)
            console.log("[A]" +i );
            for (let j = 0; j < jsonData.length; j++) {
                console.log("[B]" +i );
                data[i][j] = jsonData[j]
                console.log("[C]" +i );
            }
        }
        // 配列(data)のデータの数だけ「tr」タグを生成後、
        // 配列(data)から取得した値を各nameにセット
        for (let i = 0; i < data.length - 1; i++) {
            const parent = document.getElementById("parent");
            let tr = document.getElementsByTagName("tr")
            let tr_clone = tr[1].cloneNode(true)
            if (localStorage.key(i) != "count" && localStorage.key(i) != "data0") {
                tr_clone.id = localStorage.key(i)
                console.log(tr_clone.id);
                parent.appendChild(tr_clone)
            }
            //document.getElementsByName("department")[0].value = data[0][0]
           
            /* document.getElementsByName("department")[i].value = data[i][0]
            document.getElementsByName("employeeId")[i].value = data[i][1]
            document.getElementsByName("area")[i].value = data[i][2]
            document.getElementsByName("sales")[i].value = data[i][3]
            document.getElementsByName("customers")[i].value = data[i][4]
            document.getElementsByName("updateDate")[i].value = data[i][5] */
            
            
        }
        /* document.getElementsByName("department")[0].value = data[1][0]
            document.getElementsByName("employeeId")[0].value = data[1][1]
            document.getElementsByName("area")[0].value = data[1][2]
            document.getElementsByName("sales")[0].value = data[1][3]
            document.getElementsByName("customers")[0].value = data[1][4]
            document.getElementsByName("updateDate")[0].value = data[1][5]

            document.getElementsByName("department")[1].value = data[2][0]
            document.getElementsByName("employeeId")[1].value = data[2][1]
            document.getElementsByName("area")[1].value = data[2][2]
            document.getElementsByName("sales")[1].value = data[2][3]
            document.getElementsByName("customers")[1].value = data[2][4]
            document.getElementsByName("updateDate")[1].value = data[2][5] */
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
    for (let i = 0; i < localStorage.length+1; i++) {
        if (localStorage.key(i) != "count") {
            localStorage.removeItem("data" + i)
        }
    }
    location.reload();
}

// 「削除」を押下されたら該当の行を削除
function deleteTr(obj) {
    const defaultRow = document.getElementsByTagName("tr")
    let tr = obj.parentNode.parentNode
    //console.log(tr.id);
    if (tr.id != "data0") {
        localStorage.removeItem(tr.id)
    }

    for (let i = 0; i < localStorage.length; i++) {
        console.log(localStorage.key(i));
        if (localStorage.key(i) == tr.id) {
            localStorage.removeItem(tr.id)
        }
    }
    if (tr.id != "data0") {
        tr.parentNode.deleteRow(tr.sectionRowIndex)
    }
}

// ブラウザバック強制リロード
window.addEventListener('pageshow', () => {
    if (window.performance.navigation.type == 2) location.reload();
});