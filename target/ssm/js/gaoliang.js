/**
 * Created by dell on 2018/2/28.
 */
(function ($) {
    $.fn.GL = function (options) {
        var dataop = {
            ocolor:'red',
            oshuru:'����',
        };
        var chuancan = $.extend(dataop,options);
        $(this).each(function()//�ȱ����������һ�ε���ʽ
        {
            var _this = $(this)
            _this.find($(".glnow")).each(function()//�ҵ�����highlight���Ե�Ԫ�أ�
            {
                $(this).css({color:""});//�����ǵ�����ȥ����
            });
        });
        if(chuancan.oshuru==''){
            return false;
        }else{
            var regExp = new RegExp("(" + chuancan.oshuru.replace(/[(){}.+*?^$|\\\[\]]/g, "\\$&") + ")", "ig");//����������ʽ��g��ʾȫ�ֵģ��������g������ҵ���һ���Ͳ���������²����ˣ�
            $(this).each(function()//�������£�
            {
                var _this1 = $(this)
                var html = _this1.html();
                var newHtml = html.replace(regExp, '<span class="glnow" style="color:'+chuancan.ocolor+'">'+chuancan.oshuru+'</span>');//���ҵ��Ĺؼ����滻������highlight���ԣ�
                _this1.html(newHtml);//�������£�
            });
        }
    }
})(jQuery);


