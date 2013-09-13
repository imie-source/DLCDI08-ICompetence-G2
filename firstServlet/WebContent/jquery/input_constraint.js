

    $(document).ready(function() {
    // input only integers
    $('body').delegate('input.only_integer','keyup',function(){
    if(!$(this).val().match(/^\-?[0-9]*$/)) // numbers
    	alert ("accepte des chiffres");
//    remove_last_input(this);
    });
    // input only floats
    $('body').delegate('input.only_float','keyup',function(){
    if(!$(this).val().match(/^\-?[0-9]*[\.,]?[0-9]*$/)) // numbers[.,]numbers
    remove_last_input(this);
    });
    // input phone number
    $('body').delegate('input.only_phone_number','keyup',function(){
    if(!$(this).val().match(/^\+?[0-9 ]*$/)) // +numbers or space
    remove_last_input(this);
    });
    // input zip code
    $('body').delegate('input.only_zip_code','keyup',function(){
    if(!$(this).val().match(/^[0-9]{0,5}$/)) // 5 numbers maximum
    remove_last_input(this);
    });
    // input email
    $('body').delegate('input.only_email','keyup',function(){
    if(!$(this).val().match(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i)) // a-z and 0-9
//    	alert ("adresse mail non valide");
    	$("#email").show();
  // remove_last_input(this);
    });
//    /^[a-z][a-z0-9]*([_.-][a-z0-9]+)*@([a-z0-9]+([_.-][a-z0-9]+)*)+\.[a-z]{2,4}$/

//


    
    // input alpha-num
    $('body').delegate('input.only_alpha_num','keyup',function(){
    if(!$(this).val().match(/^[0-9a-z]*$/i)) // a-z and 0-9
    	alert ("accepte des lettres et chiffres");
//    remove_last_input(this);
    });
    // input alpha
    $('body').delegate('input.only_alpha','keyup',function(){
    if(!$(this).val().match(/^[a-z]*$/i)) // a-z
    	alert ("accepte des lettres");
//    remove_last_input(this);
    });
    // input hex
    $('body').delegate('input.only_hex','keyup',function(){
    if(!$(this).val().match(/^[0-9a-f]*$/i)) // 0-9 a-f
    remove_last_input(this);
    });
    // input oct
    $('body').delegate('input.only_oct','keyup',function(){
    if(!$(this).val().match(/^[0-7]*$/i)) // 0-7
    remove_last_input(this);
    });
    // input chemical element
    $('body').delegate('input.only_from_list','keyup',function(){
    var available_values = $(this).attr('list').split(','); // get le valid values from the 'list' attribut
    var val = $(this).val();
    if (val) { // something to analyse
    var valid_input = false;
    for (var elm in available_values) {
    if (val == available_values[elm].substr(0,val.length)) {
    valid_input = true; break;
    }
    }
    if (!valid_input)
    remove_last_input(this);
    }
    });
    }); // end of document.ready
    function remove_last_input(elm) {
    var val = $(elm).val();
    var cursorPos = elm.selectionStart;
    $(elm).val( val.substr(0,cursorPos-1) + // before cursor - 1
    val.substr(cursorPos,val.length) // after cursor
    )
    elm.selectionStart = cursorPos-1; // replace the cursor at the right place
    elm.selectionEnd = cursorPos-1;
    }

