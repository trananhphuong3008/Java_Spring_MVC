/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* global fetch */

const addToCart = (id, name, price) => {
    event.preventDefault();
    fetch("/A3mvc/api/add-cart", {
        method: 'post',
        body: JSON.stringify({
            "id": id,
            "name": name,
            "price": price,
            "quantity": 1
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(data => {
        console.info(data)
        let carts = document.getElementsByClassName('cartCounter');
        for (let i = 0; i < carts.length; i++) {
            carts[i].innerText = data.totalQuantity;
        }
    })
}

const updateCart = (id, obj) => {
    event.preventDefault();
    fetch("/A3mvc/api/update-cart", {
        method: 'put',
        body: JSON.stringify({
            "id": id,
            "quantity": obj.value
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(data => {
        console.info(data)
        let carts = document.getElementsByClassName('cartCounter');
        for (let i = 0; i < carts.length; i++) {
            carts[i].innerText = data.totalQuantity;
        }
        let amount = document.getElementsByClassName('cartAmount');
        for (let i = 0; i < amount.length; i++) {
            amount[i].innerText = data.totalAmount;
        }
    })
}

const deleteCart = (id) => {
    if (confirm("Are you sure to delete this item ?") == true) {
        event.preventDefault();
        fetch(`/A3mvc/api/delete-cart/${id}`, {
            method: 'delete'
        }).then(res => res.json()).then(data => {
            console.info(data)
            let carts = document.getElementsByClassName('cartCounter');
            for (let i = 0; i < carts.length; i++) {
                carts[i].innerText = data.totalQuantity;
            }
            let amount = document.getElementsByClassName('cartAmount');
            for (let i = 0; i < amount.length; i++) {
                amount[i].innerText = data.totalAmount;
            }

            let d = document.getElementById(`product${id}`)
            d.style.display = "none"
        })
    }
}

const pay = () => {
    if (confirm("Are you sure to pay this receipt ?")) {
        fetch("/A3mvc/api/pay", {
            method: 'post'
        }).then(res => {
            if (res.status === 201)
                location.reload();
            else
                alert("Something Wrong !!!")
        })
    }
}

const loadComments = (prductId, page = 1) => {
            fetch(`/A3mvc/api/products/${prductId}/comments?page=${page}`).then(res => res.json()).then(data => {
        let comment = document.getElementById("comments")
        for (let i = 0; i < data.length; i++) {
            let c = data[i];
            comment.innerHTML += `
            <div class="row">
                <div class="col-md-1 md-xs-4">
                  <img src="${c.user.avatar}" alt="${c.user.username}" class="rounded-cirlce img-fluid"/>
                </div>
                <div class="col-md-11 md-xs-8">
                    <p>${c.content}</p>
                    <p>${c.user.username} commented ${moment(c.createdDate)}</p>
                </div>
            </div>
`
        }
            console.info(data);

    })
}

const drawPieChart = (ctx, labels, data) => {
    const myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,//['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                    label: '# of Votes',
                    data: data,//[12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

const drawBarChart = (ctx, labels, data) => {
        const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,//['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                    label: '# of Votes',
                    data: data,//[12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}
