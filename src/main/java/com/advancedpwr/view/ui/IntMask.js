jQuery(document).ready(function()
{
	jQuery( ".intmask" ).keypress( checkForInt );
});

function checkForInt( event )
{
	var backspace_key = 8;
	var delete_key = 46;
	var tab_key = 9;
	var arrow_left_key = 37;
	var arrow_right_key = 39;
	if ( event.keyCode == backspace_key 
	  || event.keyCode == delete_key
	  || event.keyCode == tab_key
	  || event.keyCode == arrow_left_key
	  || event.keyCode == arrow_right_key )
	{
		return true;
	}
	var keychar = character( event );
	var objRegExp  = /(^\d*$)/;
	var passed = objRegExp.test( keychar );
	if( !passed )
	{
		flashField( targetElement( event ) );
	} 
	return passed;
}

var flashing = false;

function flashField( inElement )
{
	if ( flashing )
	{
		return;
	}
	var foregroundColor = inElement.style.color;
	var backgroundColor = inElement.style.backgroundColor;
	inElement.style.color = "white";
	inElement.style.backgroundColor = "red";
	flashing=true;
	setTimeout( function() {  inElement.style.color = foregroundColor; inElement.style.backgroundColor = backgroundColor; flashing=false; }, 250 );
}

function character( event )
{
	var character = "";
	if (event.which == null)
	{
     	character= String.fromCharCode(event.keyCode);    // IE
    }
  	else if (event.which != 0 && event.charCode != 0)
  	{
     	character= String.fromCharCode(event.which);	  // All others
    }
    return character;
}

function targetElement( event )
{
	if ( event.target )
	{
		return event.target;
	}
	if ( event.srcElement )
	{
		return event.srcElement;
	}
}