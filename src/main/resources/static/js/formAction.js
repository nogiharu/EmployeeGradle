
// 「複製」を押下
function addButton() {
    // 親要素を取得
    const parent = document.getElementById("parent");
    // 「tr」タグの数を取得
    let count = document.getElementsByClassName("count").length
    //--------------------------【DEPARTMENT】----------------------------
    // 「td」タグをディープクローン => nameを現在の「tr」タグの数に上書き
    const department = document.getElementById("td-department").cloneNode(true)
    department.children[0].name = "taskList[" + count + "].department"
    //--------------------------【EMPLOYEEID】----------------------------
    const employeeId = document.getElementById("td-employeeId").cloneNode(true)
    employeeId.children[0].name = "taskList[" + count + "].employeeId"
    //-----------------------------【AREA】----------------------------
    const area = document.getElementById("td-area").cloneNode(true)
    area.children[0].name = "taskList[" + count + "].area"
    //-----------------------------【SALES】----------------------------
    const sales = document.getElementById("td-sales").cloneNode(true)
    sales.children[0].name = "taskList[" + count + "].sales"
    //--------------------------【CUSTOMERS】----------------------------
    const customers = document.getElementById("td-customers").cloneNode(true)
    customers.children[0].name = "taskList[" + count + "].customers"
    //--------------------------【UPDATEDATE】----------------------------
    const updateDate = document.getElementById("td-updateDate").cloneNode(true)
    updateDate.children[0].name = "taskList[" + count + "].updateDate"
    //--------------------------------------------------------------------
    let tr = document.createElement("tr")
    tr.className = "count"
    tr.appendChild(department)
    tr.appendChild(employeeId)
    tr.appendChild(area)
    tr.appendChild(sales)
    tr.appendChild(customers)
    tr.appendChild(updateDate)
    parent.appendChild(tr)
}

// 一時保存
function saveData() {
    let tr = document.getElementsByTagName("tr")
    alert(tr.length - 1 + "件一時保存しました！")
    let data = []
    // Json形式にして「data"i"」キー名で保存
    for (let i = 0; i < tr.length; i++) {
        let data = [
            document.querySelector("select[name='taskList[" + i + "].department']").value,
            document.querySelector("select[name='taskList[" + i + "].employeeId']").value,
            document.querySelector("input[name='taskList[" + i + "].area']").value,
            document.querySelector("input[name='taskList[" + i + "].sales']").value,
            document.querySelector("input[name='taskList[" + i + "].customers']").value,
            document.querySelector("input[name='taskList[" + i + "].updateDate']").value,
        ]
        localStorage.setItem("data" + i, JSON.stringify(data))
    }
}

// 画面読み込み時、ローカルストレージにデータがあれば表示
window.onload = function () {
    
    if (localStorage.length) {
        let data = new Array(localStorage.length)
        for (let i = 0; i < data.length; i++) {
            //「data"i"」キー名を取得
            let json = JSON.parse(localStorage.getItem("data" + i))
            data[i] = new Array(json.length)
            for (let j = 0; j < json.length; j++) {
                //「data[i][j]」にJsonに保存したValueを格納
                data[i][j] = json[j]
            }
        }

        // 入力タグのidを取得
        let selectDepartment = document.getElementById("select-department")
        let selectEmployeeId = document.getElementById("select-employeeId")
        let inputArea = document.getElementById("input-area")
        let inputSales = document.getElementById("input-sales")
        let inputCustomers = document.getElementById("input-customers")
        let inputUpdateDate = document.getElementById("input-updateDate")

        // 初期表示nameの「taskList[0]*」に「data配列」の値を格納
        selectDepartment.value = data[0][0]
        selectEmployeeId.value = data[0][1]
        inputArea.value = data[0][2]
        inputSales.value = data[0][3]
        inputCustomers.value = data[0][4]
        inputUpdateDate.value = data[0][5]

        // name属性「taskList[1]*」以降に「data配列」の値を格納
        for (let i = 1; i < data.length; i++) {
            const parent = document.getElementById("parent");
            //--------------------------【DEPARTMENT】----------------------------
            const department = document.getElementById("td-department").cloneNode(true)
            department.children[0].name = "taskList[" + i + "].department"
            department.children[0].value = data[i][0]
            //--------------------------【EMPLOYEEID】----------------------------
            const employeeId = document.getElementById("td-employeeId").cloneNode(true)
            employeeId.children[0].name = "taskList[" + i + "].employeeId"
            employeeId.children[0].value = data[i][1]
            //-----------------------------【AREA】----------------------------
            const area = document.getElementById("td-area").cloneNode(true)
            area.children[0].name = "taskList[" + i + "].area"
            area.children[0].value = data[i][2]
            //-----------------------------【SALES】----------------------------
            const sales = document.getElementById("td-sales").cloneNode(true)
            sales.children[0].name = "taskList[" + i + "].sales"
            sales.children[0].value = data[i][3]
            //--------------------------【CUSTOMERS】----------------------------
            const customers = document.getElementById("td-customers").cloneNode(true)
            customers.children[0].name = "taskList[" + i + "].customers"
            customers.children[0].value = data[i][4]
            //--------------------------【UPDATEDATE】----------------------------
            const updateDate = document.getElementById("td-updateDate").cloneNode(true)
            updateDate.children[0].name = "taskList[" + i + "].updateDate"
            updateDate.children[0].value = data[i][5]
            //--------------------------------------------------------------------
            let tr = document.createElement("tr")
            tr.className = "count"
            tr.appendChild(department)
            tr.appendChild(employeeId)
            tr.appendChild(area)
            tr.appendChild(sales)
            tr.appendChild(customers)
            tr.appendChild(updateDate)
            parent.appendChild(tr)
        }
    }
}
// 「確定」が押下されたらローカルストレージをクリア
let submit = document.getElementById("submit")
submit.addEventListener("submit", function () {
    localStorage.clear()
})
