// 鍵生成実行ボタン押下
$('#exec-keygen').on('click', function() {
    $('#private-key-result').val('');
    $('#public-key-result').val('');
    $('#keygen-err-msg').text('');
    $.ajax('/exec-keygen', {
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            keyType: $('#key-type').val(),
            keyLength: $('#key-length').val()
        }),
        dataType: 'JSON'
    })
    .done(function(res) {
        if (res.result == "OK") {
            $('#private-key-result').val(res.key.private);
            $('#public-key-result').val(res.key.public);
        } else {
            $('#keygen-err-msg').text(res.message);
        }
    })
    .fail(function() {
        $('#keygen-err-msg').text('エラーが発生しました。');
    });
});

// 鍵生成クリアボタン押下
$('#clear-keygen').on('click', function() {
    $('#key-type').find('option')[0].selected = true;
    $("#key-length").find('option')[0].selected = true;
    $('#private-key-result').val('');
    $('#public-key-result').val('');
    $('#keygen-err-msg').text('');
});

// 暗号化実行ボタン押下
$('#exec-encrypt').on('click', function() {
    $('#encrypt-result').val('');
    $('#encrypt-err-msg').text('');
    $.ajax('/exec-encrypt', {
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            algorithm: $('#encrypt-algorithm').val(),
            key: $('#encrypt-key').val(),
            iv: $('#encrypt-iv').val(),
            data: $('#encrypt-data').val()
        }),
        dataType: 'JSON'
    })
    .done(function(res) {
        if (res.result == "OK") {
            $('#encrypt-result').val(res.encrypted);
            if (res.iv != null) {
                $('#encrypt-iv').val(res.iv);
            }
        } else {
            $('#encrypt-err-msg').text(res.message);
        }
    })
    .fail(function() {
        $('#encrypt-err-msg').text('エラーが発生しました。');
    });
});

// 暗号化クリアボタン押下
$('#clear-encrypt').on('click', function() {
    $('#encrypt-algorithm').find('option')[0].selected = true;
    $('#encrypt-key').val('');
    $('#encrypt-iv').val('');
    $('#encrypt-data').val('');
    $('#encrypt-err-msg').text('');
});

// 復号実行ボタン押下
$('#exec-decrypt').on('click', function() {
    $('#decrypt-result').val('');
    $('#decrypt-err-msg').text('');
    $.ajax('/exec-decrypt', {
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            algorithm: $('#decrypt-algorithm').val(),
            key: $('#decrypt-key').val(),
            iv: $('#decrypt-iv').val(),
            data: $('#decrypt-data').val()
        }),
        dataType: 'JSON'
    })
    .done(function(res) {
        if (res.result == "OK") {
            $('#decrypt-result').val(res.decrypted);
        } else {
            $('#decrypt-err-msg').text(res.message);
        }
    })
    .fail(function() {
        $('#decrypt-err-msg').text('エラーが発生しました。');
    });
});

// 復号クリアボタン押下
$('#clear-decrypt').on('click', function() {
    $('#decrypt-algorithm').find('option')[0].selected = true;
    $('#decrypt-key').val('');
    $('#decrypt-iv').val('');
    $('#decrypt-data').val('');
    $('#decrypt-err-msg').text('');
});

// 署名実行ボタン押下
$('#exec-sign').on('click', function() {
    $('#signature-result').val('');
    $('#signature-err-msg').text('');
    $.ajax('/exec-sign', {
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            algorithm: $('#signature-algorithm').val(),
            key: $('#signature-key').val(),
            data: $('#signature-target-data').val()
        }),
        dataType: 'JSON'
    })
    .done(function(res) {
        if (res.result == "OK") {
            $('#signature-result').val(res.signature);
        } else {
            $('#signature-err-msg').text(res.message);
        }
    })
    .fail(function() {
        $('#signature-err-msg').text('エラーが発生しました。');
    });
});

// 署名クリアボタン押下
$('#clear-sign').on('click', function() {
    $('#signature-algorithm').find('option')[0].selected = true;
    $('#signature-key').val('');
    $('#signature-target-data').val('');
    $('#signature-result').val('');
    $('#signature-err-msg').text('');
});

// 署名検証実行ボタン押下
$('#exec-verify').on('click', function() {
    $('#verify-result').val('');
    $('#verify-err-msg').text('');
    $.ajax('/exec-verify', {
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            algorithm: $('#verify-algorithm').val(),
            key: $('#verify-key').val(),
            sign: $('#verify-sign').val(),
            data: $('#verify-data').val()
        }),
        dataType: 'JSON'
    })
    .done(function(res) {
        if (res.result == "OK") {
            $('#verify-result').val(res.verify_result);
        } else {
            $('#verify-err-msg').text(res.message);
        }
    })
    .fail(function() {
        $('#signature-err-msg').text('エラーが発生しました。');
    });
});

// 署名検証クリアボタン押下
$('#clear-verify').on('click', function() {
    $('#verify-algorithm').find('option')[0].selected = true;
    $('#verify-key').val('');
    $('#verify-sign').val('');
    $('#verify-data').val('');
    $('#verify-result').val('');
    $('#verify-err-msg').text('');
});