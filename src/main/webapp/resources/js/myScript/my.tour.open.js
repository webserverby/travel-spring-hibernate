
    $(document).ready(function() {
        $(".fancybox").fancybox({
            openEffect	: 'none',
            closeEffect	: 'none',
            nextEffect : 'fade',
            prevEffect : 'fade',
            padding : 0,
            maxWidth	: 700,
            maxHeight	: 430,
            helpers : {
                overlay : {
                    locked : false
                }
            }
        });
    });

    $(document).ready(function() {
        $("#mapTour").fancybox({
            openEffect	: 'none',
            closeEffect	: 'none',
            padding : 0,
            helpers : {
                overlay : {
                    locked : false
                }
            }
        });
    });

    function get_map(cX,cY){
        $('#map').remove();
        $('#mapCity').append(
            '<div class="map" id="map"></div>'
        );
        initMap(cX,cY);
    }

    $(document).ready(function(){
        $(".ticket__airline-logo").find("img").mousemove(function(e){
            var hint = $(this).attr('alt');
            $('#hint').css({'left': e.clientX + 40, 'top': e.clientY + 10});
            $('#hint').show().text(hint);
        }).mouseout(function(){
            $('#hint').hide();
        });
    });