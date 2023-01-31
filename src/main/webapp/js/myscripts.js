//update ids
var upd_sale_id = 0;
var upd_purchase_id = 0;
var upd_damage_id = 0;
var upd_product_id = 0;
var upd_person_id = 0;
var settings_purchase_id = 0;
var upd_debts_id =0;
var upd_paydebts_id =0;
var upd_expense_id =0;
$(document).ready(function () {
//    alert('js is working');
// ---saving---------
    save_prod();
    save_purc();
    save_dam();
    save_sales();
    save_users();
    save_debts;
    save_paydebts();
    save_expenses();
    Login();
//-------deleting------
    delete_sale();
    delete_purchase();
    delete_product();
    delete_damage();
    delete_user();
//-------Updating------
    update_sale();
    update_purchase();
    update_damage();
    update_product();
    update_users();
//------Combo box------
    cbo_prod_selected();
    cbo_sl_selected();
    cbo_sl_cost_selected();
    save_settings();
// ------date input----
    date_input();
//    alert('js running ...');

});

function save_prod() {
    $('#btn_save_prod').click(function (e) {//Happen only when the user clicks the button
        e.preventDefault();
        var product_id = upd_product_id;

        var txt_name = $('#txt_name').val();
        if (product_id === 0) {//save
            product_form_fields(null, txt_name);
            //fields
        } else {//update
            product_form_fields(product_id, txt_name);
        }
    });
}

function save_purc() {
    $('#btn_save_pur').click(function (e) {
        e.preventDefault();
        var purchase_id = upd_purchase_id;
        //fields
        var txt_product = $('#txt_product').val(),
                txt_purchase_qty = $('#txt_purchase_qty').val(),
                txt_current_qty = $('#txt_current_qty').val(),
                txt_person = $('#txt_person').val(),
                txt_unit_cost = $('#txt_unit_cost').val();


        if (purchase_id === 0) {//save
            purchase_form_fields(null, txt_product, txt_purchase_qty, txt_current_qty, txt_person, txt_unit_cost);
            //fields
        } else {//update
            purchase_form_fields(purchase_id, txt_product, txt_purchase_qty, txt_current_qty, txt_person, txt_unit_cost);
        }

    });
}

function save_sales() {
    $('#btn_save_sales').click(function (e) {

        e.preventDefault();
        var sale_id = upd_sale_id;

        var txt_product = $('#txt_product').val(),
                txt_sold_qty = $('#txt_sold_qty').val(),
                txt_current_qty = $('#txt_current_qty').val(),
                txt_person = $('#txt_person').val(),
                txt_sale_cost = $('#txt_sale_cost').val(),
                txt_amt_paid = $('#txt_amt_paid').val();

        if (sale_id === 0) {//save
            sale_form_fields(null, txt_product, txt_sold_qty, txt_current_qty, txt_person, txt_sale_cost,txt_amt_paid);
        } else {//update
            sale_form_fields(sale_id, txt_product, txt_sold_qty, txt_current_qty, txt_person, txt_sale_cost,txt_amt_paid);
        }

    });
}

$.ajax({
    type: 'GET',
    url: 'api/ajaxrest/demo4',
    dataType: 'json',
    contentType: 'application/json',
    success: function (result) {
        var s = '';
        for (var i = 0; i < result.length; i++) {
            s += '<br/> Unit id:  ' + result[i].unit_id;
            s += '<br/> Structure:  ' + result[i].structure;
            s += '<br/> Name:  ' + result[i].name;
            s += '<br/> =========================';
            $('#result4').html(s);
        }
    }
});
function save_dam() {
    $('#btn_save_dam').click(function (e) {
        e.preventDefault();
        //fields
        var damage_id = upd_damage_id;
        var txt_product = $('#txt_product').val(),
                txt_damage_qty = $('#txt_damage_qty').val();
        if (damage_id === 0) {//save
            damage_form_fields(null, txt_product, txt_damage_qty);
        } else {//update
            damage_form_fields(damage_id, txt_product, txt_damage_qty);
        }

    });
}

function save_users() {

    $('#btn_save_users').click(function (e) {
        e.preventDefault();

        var person_id = upd_person_id;

        var txt_name = $('#txt_name').val(),
                txt_surname = $('#txt_surname').val(),
                txt_category = $('#txt_category').val(),
                txt_gender = $('#txt_gender').val(),
                txt_phone_number = $('#txt_phone_number').val(),
                txt_username = $('#txt_username').val(),
                txt_password = $('#txt_password').val();
        if (person_id === 0) {//save
            person_form_fields(null, txt_name, txt_surname, txt_category, txt_gender, txt_phone_number, txt_username, txt_password);
        } else {//update
            person_form_fields(person_id, txt_name, txt_surname, txt_category, txt_gender, txt_phone_number, txt_username, txt_password);
        }

    });
}

function save_settings() {
    $('#btn_settings').click(function (e) {
        e.preventDefault();
        //fields
        var purchase_id = settings_purchase_id;
        var txt_product = $('#txt_product').val();
        var txt_sale_cost = $('#txt_sale_cost').val();

        if (purchase_id === 0) {//save
            settings_form_fields(null, txt_product, txt_sale_cost);
        } else {//update
            settings_form_fields(purchase_id, txt_product, txt_sale_cost);
        }

    });
}
function save_debts() {
    $('#btn_save_debts').click(function (e) {//Happen only when the user clicks the button
        e.preventDefault();
        var debts_id = upd_debts_id;

        var txt_person = $('#txt_person').val();
        var txt_product = $('#txt_product').val();
        var txt_amount = $('#txt_amount').val();
        var txt_total_debts = $('#txt_total_debts').val();
        if (debts_id === 0) {//save
            debts_form_fields(null, txt_person,txt_product,txt_amount,txt_total_debts);
            //fields
        } else {//update
            debts_form_fields(debts_id, txt_person,txt_product,txt_amount,txt_total_debts);
        }
    });
}
function save_paydebts() {
    $('#btn_save_paydebts').click(function (e) {//Happen only when the user clicks the button
        e.preventDefault();
        var paydebts_id = upd_paydebts_id;

        var txt_person = $('#txt_person').val();
        var txt_product = $('#txt_product').val();
        var txt_amt_paid = $('#txt_amt_paid').val();
        if (paydebts_id === 0) {//save
            paydebts_form_fields(null, txt_person,txt_product,txt_amt_paid);
            //fields
        } else {//update
            paydebts_form_fields(paydebts_id, txt_person,txt_product,txt_amt_paid);
        }
    });
}

function save_expenses() {
    $('#btn_save_expenses').click(function (e) {//Happen only when the user clicks the button
        e.preventDefault();
        var expense_id = upd_expense_id;

        var txt_name = $('#txt_name').val();
        var txt_amount = $('#txt_amount').val();
        if (expense_id === 0) {//save
            expense_form_fields(null, txt_name,txt_amount);
            //fields
        } else {//update
            expense_form_fields(expense_id, txt_name,txt_amount);
        }
    });
}

function Login() {

    $('#btn_login').click(function (e) {
        e.preventDefault();
        var username = $('#txt_username').val();
        var password = $('#txt_password').val();
        if (username !== '' && password !== '') {



            $.post($('.root').val() + '/login', {username: username, password: password},
                    function (data) {
                        if ('success' === data) {
                            window.location.replace($('.root').val() + '/forms/admin_dashboard.jsp');
                            $('#txt_username').val('');
                            $('#txt_password').val('');
                        } else {
                            alert(data);
                        }

                    });
        } else {
            alert('You have to enter all the fields');
        }


    });
}

function delete_sale() {
    $('.link_sale').click(function () {

        var sale_id = $(this).data('bind');

        if (confirm('Do you really want to delete this record?')) {
            $.ajax({
                type: 'get',
                url: $('.root').val() + '/sales?action=delete&sale_id=' + sale_id,
                success: function (data) {
                    alert(data);
                }
            }).done(function () {
                window.location.reload();
            });
        }

        return false;
    });
}
function delete_purchase() {
    $('.link_purchase').click(function () {

        var purchase_id = $(this).data('bind');
        if (confirm('Do you really want to delete this record?')) {
            $.ajax({
                type: 'get',
                url: $('.root').val() + '/purchase?action=delete&purchase_id=' + purchase_id,
                success: function (data) {
                    alert(data);
                }
            }).done(function () {
                window.location.reload();
            });
        }

        return false;
    });
}
function delete_product() {
    $('.link_product').click(function () {

        var product_id = $(this).data('bind');
        if (confirm('Do you really want to delete this record?')) {
            $.ajax({
                type: 'get',
                url: $('.root').val() + '/product?action=delete&product_id=' + product_id,
                success: function (data) {
                    alert(data);
                }
            }).done(function () {
                window.location.reload();
            });
        }

        return false;
    });
}
function delete_damage() {
    $('.link_damage').click(function () {

        var damage_id = $(this).data('bind');
        if (confirm('Do you really want to delete this record?')) {
            $.ajax({
                type: 'get',
                url: $('.root').val() + '/damages?action=delete&damage_id=' + damage_id,
                success: function (data) {
                    alert(data);
                }
            }).done(function () {
                window.location.reload();
            });
        }

        return false;
    });
}
function delete_user() {
    $('.link_user').click(function () {

        var person_id = $(this).data('bind');
        if (confirm('Do you really want to delete this record?')) {
            $.ajax({
                type: 'get',
                url: $('.root').val() + '/Users?action=delete&person_id=' + person_id,
                success: function (data) {
                    alert(data);
                }
            }).done(function () {
                window.location.reload();
            });
        }

        return false;
    });
}

function update_sale() {

    $('.link_update_sale').click(function () {
        var sale_id = $(this).data('bind');
        $.ajax({
            type: 'get',
            url: $('.root').val() + '/sales?action=edit&sale_id=' + sale_id,
            success: function (data) {

                var sales = JSON.parse(data).sales;
                console.log(sales.sale_id);
                upd_sale_id = sales.sale_id;
                var txt_product = sales.product,
                        txt_sold_qty = sales.sold_qty,
                        txt_current_qty = sales.current_qty,
                        txt_sale_cost = sales.sale_cost,
                        txt_person = sales.person;

                $('#txt_product').val(txt_product);
                $('#txt_sold_qty').val(txt_sold_qty);
                $('#txt_current_qty').val(txt_current_qty);
                $('#txt_sale_cost').val(txt_sale_cost);
                $('#txt_person').val(txt_person);
            }
        }).done(function () {
        });
        $('#btn_cancel_update').show(0).removeClass('d-none');
        $('#btn_save_sales').html('Update');
        return false;
    });
}
function update_purchase() {

    $('.link_update_purchase').click(function () {

        var purchase_id = $(this).data('bind');

        $.ajax({
            type: 'get',
            url: $('.root').val() + '/purchase?action=edit&purchase_id=' + purchase_id,
            success: function (data) {

                var purchases = JSON.parse(data).purchases;
                console.log(purchases);
                console.log(purchases.purchase_id);
                upd_purchase_id = purchases.purchase_id;
                var txt_product = purchases.product,
                        txt_purchase_qty = purchases.purchase_qty,
                        txt_current_qty = purchases.current_qty,
                        txt_person = purchases.person;

                $('#txt_product').val(txt_product);
                $('#txt_purchase_qty').val(txt_purchase_qty);
                $('#txt_current_qty').val(txt_current_qty);
                $('#txt_person').val(txt_person);
            }
        }).done(function () {
        });
        $('#btn_cancel_update').show(0).removeClass('d-none');
        $('#btn_save_pur').html('Update');
        return false;
    });
}
function update_damage() {
    $('.link_update_damage').click(function () {
        var damage_id = $(this).data('bind');
        $.ajax({
            type: 'get',
            url: $('.root').val() + '/damages?action=edit&damage_id=' + damage_id,
            success: function (data) {

                var damages = JSON.parse(data).damages;
                console.log(damages.damage_id);
                upd_damage_id = damages.damage_id;
                var txt_product = damages.product,
                        txt_damage_qty = damages.damage_qty;
                $('#txt_product').val(txt_product);
                $('#txt_damage_qty').val(txt_damage_qty);
            }

        }).done(function () {
            $('#btn_cancel_update').show(0).removeClass('d-none');
            $('#btn_save_dam').html('Update');
        });
    });
}
function update_product() {

    $('.link_update_product').click(function () {
        var product_id = $(this).data('bind');
        upd_product_id = product_id;
        $.ajax({
            type: 'get',
            url: $('.root').val() + '/product?action=edit&product_id=' + product_id,
            success: function (data) {

                var products = JSON.parse(data).products;
                console.log(products.product_id);
                upd_product_id = products.product_id;
                var txt_name = products.name,
                        txt_quantity = products.quantity,
                        txt_unit_cost = products.unit_cost,
                        txt_sale_cost = products.sale_cost;

                $('#txt_name').val(txt_name);
                $('#txt_quantity').val(txt_quantity);
                $('#txt_unit_cost').val(txt_unit_cost);
                $('#txt_sale_cost').val(txt_sale_cost);
            }

        }).done(function () {
            $('#btn_cancel_update').show(0).removeClass('d-none');
            $('#btn_save_prod').html('Update');
        });
    });
}
function update_users() {
    $('.link_update_users').click(function () {
        var person_id = $(this).data('bind');
        upd_person_id = person_id;
        $.ajax({
            type: 'get',
            url: $('.root').val() + '/Users?action=edit&person_id=' + person_id,
            success: function (data) {

                var persons = JSON.parse(data).persons;
                var account = JSON.parse(data).account;

                upd_person_id = persons.person_id;

                var txt_name = persons.name,
                        txt_surname = persons.surname,
                        txt_category = persons.category,
                        txt_gender = persons.gender,
                        txt_phone_number = persons.phone_number,
                        txt_username = account.username,
                        txt_password = account.password;

//                alert(txt_password);
                $('#txt_name').val(txt_name);
                $('#txt_surname').val(txt_surname);
                $('#txt_category').val(txt_category);
                $('#txt_gender').val(txt_gender);
                $('#txt_phone_number').val(txt_phone_number);
                $('#txt_username').val(txt_username);
                $('#txt_password').val(txt_password);
            }

        }).done(function () {
            $('#btn_cancel_update').show(0).removeClass('d-none');
            $('#btn_save_users').html('Update');
        });
        return false;
    });
}
//concel button
//<editor-fold defaultstate="collapsed" desc="-----form values post">
function  sale_form_fields(sale_id, txt_product, txt_sold_qty, txt_current_qty, txt_person, txt_sale_cost,txt_amt_paid) {// update
    if (txt_product !== '' && txt_sold_qty !== '' && txt_current_qty !== '' && txt_person !== '' && txt_sale_cost !== '' && txt_amt_paid!=='') {

        $.post($('.root').val() + '/sales', {sale_id: sale_id, txt_product: txt_product, txt_sold_qty: txt_sold_qty, txt_current_qty: txt_current_qty, txt_person: txt_person, txt_sale_cost: txt_sale_cost,txt_amt_paid:txt_amt_paid}, function (data) {
            alert(data);
            $('#txt_product').val('');
            $('#txt_sold_qty').val('');
            $('#txt_current_qty').val('');
            $('#txt_person').val('');
            $('#txt_sale_cost').val('');
            $('#txt_amt_paid').val('');
            window.location.reload();
        });
    } else {
        alert('You have to enter all the fields');
    }
}
function purchase_form_fields(purchase_id, txt_product, txt_purchase_qty, txt_current_qty, txt_person, txt_unit_cost) {

    if (txt_product !== '' && txt_purchase_qty !== '' && txt_current_qty !== '' && txt_person !== '' && txt_unit_cost !== '') {
        $.post($('.root').val() + '/purchase',
                {purchase_id: purchase_id, txt_product: txt_product, txt_purchase_qty: txt_purchase_qty, txt_current_qty: txt_current_qty, txt_person: txt_person, txt_unit_cost: txt_unit_cost},
                function (data) {
                    alert(data);
                    $('#txt_product').val('');
                    $('#txt_purchase_qty').val('');
                    $('#txt_current_qty').val('');
                    $('#txt_person').val('');
                    $('#txt_unit_cost').val('');

                    window.location.reload();
                });
    } else {
        alert('You have to enter all the fields');
    }
}
function damage_form_fields(damage_id, txt_product, txt_damage_qty) {

    if (txt_product !== '' && txt_damage_qty !== '') {
        $.post($('.root').val() + '/damages', {damage_id: damage_id, txt_product: txt_product, txt_damage_qty: txt_damage_qty}, function (data) {
            alert(data);
            $('#txt_product').val('');
            $('#txt_damage_qty').val('');
            window.location.reload();
        });
    } else {
        alert('You have to enter all the fields');
    }
}

function settings_form_fields(purchase_id, txt_product, txt_sale_cost) {

    if (txt_product !== '' && txt_sale_cost !== '') {
        $.post($('.root').val() + '/settings', {purchase_id: purchase_id, txt_product: txt_product, txt_sale_cost: txt_sale_cost}, function (data) {
            alert(data);
            $('#txt_product').val('');
            $('#txt_sale_cost').val('');
            window.location.reload();
        });
    } else {
        alert('You have to enter all the fields');
    }
}

function product_form_fields(product_id, txt_name) {

    if (txt_name !== '') {
        $.post($('.root').val() + '/product', {product_id: product_id, txt_name: txt_name},
                function (data) {
                    alert(data);
                    $('#txt_name').val('');
                    window.location.reload();
                });
    } else {
        alert('You have to enter all the fields');
    }
}
function person_form_fields(person_id, txt_name, txt_surname, txt_category, txt_gender, txt_phone_number, txt_username, txt_password) {
    console.log(txt_phone_number);
//     && txt_username !== '' && txt_password !== ''


    if (txt_name !== '' && txt_surname !== '' && txt_category !== '' && txt_gender !== '' && txt_phone_number !== '') {


        $.post($('.root').val() + '/Users', {person_id: person_id, txt_name: txt_name, txt_surname: txt_surname, txt_category: txt_category, txt_gender: txt_gender, txt_phone_number: txt_phone_number, txt_username: txt_username, txt_password: txt_password}, function (data) {
            alert(data);
            $('#txt_name').val(''),
            $('#txt_surname').val('');
            $('#txt_gender').val('');
            $('#txt_phone_number').val('');
            $('#txt_username').val('');
            $('#txt_password').val('');
            window.location.reload();
        });
    } else {
        alert('You have to enter all the fields');
    }
}

function debts_form_fields(debts_id, txt_person,txt_product,txt_amount,txt_total_debts) {

    if (txt_person !== '' && txt_product !==''&& txt_amount !=='' && txt_total_debts !=='' ) {
        $.post($('.root').val() + '/debts', {debts_id: debts_id, txt_person: txt_person,txt_product:txt_product,txt_amount:txt_amount,txt_total_debts:txt_total_debts},
                function (data) {
                    alert(data);
                    $('#txt_person').val('');
                    $('#txt_product').val('');
                    $('#txt_amount').val('');
                    $('#txt_total_debts').val('');
                    window.location.reload();
                });
    } else {
        alert('You have to enter all the fields');
    }
}
function paydebts_form_fields(paydebts_id, txt_person,txt_product,txt_amt_paid) {

    if (txt_person !== '' && txt_product !==''&& txt_amt_paid !=='' ) {
        $.post($('.root').val() + '/paydebts', {paydebts_id: paydebts_id, txt_person: txt_person,txt_product:txt_product,txt_amt_paid:txt_amt_paid},
                function (data) {
                    alert(data);
                    $('#txt_person').val('');
                    $('#txt_product').val('');
                    $('#txt_amt_paid').val('');
                    window.location.reload();
                });
    } else {
        alert('You have to enter all the fields');
    }
}

function expense_form_fields(expense_id, txt_name,txt_amount) {

    if (txt_name !== '' && txt_amount !=='' ) {
        $.post($('.root').val() + '/expenses', {expense_id: expense_id, txt_name: txt_name,txt_amount:txt_amount},
                function (data) {
                    alert(data);
                    $('#txt_name').val('');
                    $('#txt_amount').val('');
                    window.location.reload();
                });
    } else {
        alert('You have to enter all the fields');
    }
}
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="---cancel update-----">
$('#btn_cancel_update').click(function () {
    upd_sale_id = 0;
    window.location.reload();
});
$('#btn_cancel_update').click(function () {
    upd_purchase_id = 0;
    window.location.reload();
});
$('#btn_cancel_update').click(function () {
    upd_damage_id = 0;
    window.location.reload();
});
$('#btn_cancel_update').click(function () {
    upd_product_id = 0;
    window.location.reload();
});
$('#btn_cancel_update').click(function () {
    upd_person_id = 0;
    window.location.reload();
});
//</editor-fold>

function date_input() {
    $('.date_input').datepicker({
        dateFormat: 'yy-mm-dd', 
        maxDate: new Date()
    });
}
function cbo_prod_selected() {
    $('.cbo_pur_pro').change(function () {
        try {
            var txt_product = $(this).val();

            $.ajax({
                type: 'get', dataType: 'json',
                url: $('.root').val() + '/purchase?action=get_pro_qty&txt_product=' + txt_product,
                success: function (res) {
                    var qty = res.get_pro_qty;
                    $('#txt_current_qty').val(qty);
                },
                error: function (a, b, c) {
                    alert(c);
                }

            }).done(function () {
                var user_cat = $('#loggedin_user_cat').val();
                if (user_cat !== 'admin') {
                    $('#txt_current_qty').prop('disabled', true);
                }

            });

        } catch (err) {
            alert(`Error occured ${err}`);
        }
    });
}
function cbo_sl_selected() {
    $('.cbo_sale').change(function () {//combo box for product name

        try {
            var txt_product = $(this).val();
            $.ajax({
                type: 'get', dataType: 'json',
                url: $('.root').val() + '/sales?action=get_pro_qty&txt_product=' + txt_product,
                success: function (res) {
                    var qty = res.get_pro_qty;
                    $('#txt_current_qty').val(qty);
                },
                error: function (a, b, c) {
                    alert(c);
                }

            }).done(function () {
                var user_cat = $('#loggedin_user_cat').val();
                if (user_cat !== 'admin') {
                    $('#txt_current_qty').prop('disabled', true);
                }
            });
        } catch (err) {
            alert(`Error occured ${err}`);
        }
    });
}
function cbo_sl_cost_selected() {// combo box for sale cost
    $('.cb_sale_cost').change(function () {
//             alert('javascript is working');
        try {
            var txt_product = $(this).val();
            $.ajax({
                type: 'get', dataType: 'json',
                url: $('.root').val() + '/sales?action=get_sl_cost&txt_product=' + txt_product,
                success: function (res) {
                    var qty = res.get_sl_cost;
                    $('#txt_sale_cost').val(qty);
                },
                error: function (a, b, c) {
                    alert(c);
                }

            }).done(function () {
                var user_cat = $('#loggedin_user_cat').val();
//                $('#txt_current_qty').prop('disabled', true);
                if (user_cat === 'admin') {
//                    $('#txt_sale_cost').prop('disabled', true);
                }

            });
        } catch (err) {
            alert(`Error occured ${err}`);
        }
    });
}






