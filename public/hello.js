$(document).ready(function() {
	convertImgToBase64($('img').attr('src'), function(base64img){		
		$.ajax({
	        url: "http://example.com.vn:9000/updateImage",
	        method: 'POST',
	        data: {image: base64img}
	    }).then(function(data, status, jqxhr) {
	       $('.greeting-id').append(data.id);       
	       console.log(jqxhr);
	    });
	});
    
});

function convertImgToBase64(url, callback, outputFormat){
    var canvas = document.createElement('CANVAS'),
        ctx = canvas.getContext('2d'),
        img = new Image;
    img.crossOrigin = 'Anonymous';
    img.onload = function(){
        var dataURL;
        canvas.height = img.height;
        canvas.width = img.width;
        ctx.drawImage(img, 0, 0);
        dataURL = canvas.toDataURL(outputFormat);
        callback.call(this, dataURL);
        canvas = null; 
    };
    img.src = url;
}