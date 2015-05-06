KISSY.use('node, date/picker, date/format,date/picker/assets/dpl.css', function(S, Node, DatePicker, DateFormat) {
    var result = Node.all("#result");
    var picker = new DatePicker({
        disableDate: function(current, value) {
            return current.getMonth() < value.getMonth();
        },
        render: "#holder"
    }).render();

    picker.on('select', function(e) {
        var value = e.value;
        if (value) {
            result.val(DateFormat.getDateInstance(DateFormat.Style.MEDIUM).format(value));
            Node.one("#holder").hide();
        } else {
            result.val(null);
        }
    });

    Node.one("#holder").hide();

    Node.one("#result").on('click', function(event){
    	Node.one("#holder").show();
    });
});

KISSY.use('node', function(S, Node) {
	Node.all(".unstruct").on('click', function(){
		var self = Node.one(this);
		var text = self.first().html();
		var index = text.indexOf("+");
		if(index==0){
			self.first().html(text.replace(/\+/, '-'));
			self.next().show();
		} else {
			self.first().html(text.replace(/-/, '+'));
			self.next().hide();
		}
	});
});