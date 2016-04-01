////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Handlers

$(document).ready(function() {
    
    get_user();
    get_profilePosts();
    //get_profileImages();
    
   $('#username_box').click(function(){
        load_profile($('#username').html());
    });
    
    $('#logout').click(function() {
        logout();
    });

}); // end of document ready function

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// Functions

/////////////////////////////////////////////////////////////////////////////////
//	get_profilePosts
//
//		Gets posts from the database to display on the wall
//
function get_profilePosts(){
    var data = {'op': 'get user posts'};
    var script = 'src/php/handler.php';
    var func = get_profilePosts_callback;
    
    console.log('Getting profile post html');
    ajax(data,script,func);
}

/*********************************************************************************************
 *  set post handlers
 */

/////////////////////////////////////////////////////////////////////////////////
//	set_profilePosts_handlers
//
//		Sets all handlers needed by the wall elements
//
function set_profilePosts_handlers(loggedin){
    set_allPostHandlers();
    if (loggedin) {
        set_createPostHandlers();
    } else {
        $('.fab').hide();
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
/// Callback functions

/////////////////////////////////////////////////////////////////////////////////
//	get_profilePosts_callback
//
//		Receives html containing the wall posts and adds it to
//      the wall.
//
function get_profilePosts_callback(data){
    console.log("Gathered all posts for the profile");
    //$('.wall').html(data);
    obj = JSON.parse(data);
    $('.wall').html(obj.html);
    
    console.log("returned loggedin: " + obj.loggedin);
    
    set_profilePosts_handlers(obj.loggedin);
}