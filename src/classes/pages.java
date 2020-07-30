package classes;

public class pages {

    public static String isActive(int num, int page) {
        if (num == page) {
            return "active";
        } else {
            return "";
        }
    }

    public static String creatPageNumber(String source, int sum, int page, String country, String city, String theme, String title, String description) {
        StringBuilder stringBuilder = new StringBuilder();
        int total = (int) Math.ceil(sum / 9);
        if (total > 1 && total < 6) {
            if (page > 0) {
                stringBuilder.append("<a href='" + changePage(0, source, country, city, theme, title, description) + "'>首页</a>" +
                        "<a href='" + changePage(page - 1, source, country, city, theme, title, description) + "'><</a>");
            }
            for (int i = 0; i < total; i++) {
                stringBuilder.append("<a href='" + changePage(i, source, country, city, theme, title, description) + "' class='" + isActive(i, page) + "'>" + (i + 1) + "'</a>'");
            }
            if (page < total - 1) {
                stringBuilder.append("<a href='" + changePage(page + 1, source, country, city, theme, title, description) + "'>></a>'");
                stringBuilder.append("<a href='" + changePage(total - 1, source, country, city, theme, title, description) + "'>尾页</a>");
            }
        } else if (total > 5) {
            if (page > 1) {
                stringBuilder.append("<a href='" + changePage(0, source, country, city, theme, title, description) + "'>首页</a>" +
                        "<a href='" + changePage(page - 1, source, country, city, theme, title, description) + "' ><</a > ");
            }
            if (page < 2) {
                for (int i = 0; i < 5; i++) {
                    stringBuilder.append("<a href='" + changePage(i, source, country, city, theme, title, description) + "' class='" + isActive(i, page) + "'>" + (i + 1) + "</a>");
                }
            } else if (page + 2 < total) {
                for (int i = page - 2; i <= page + 2 && i < total; i++) {
                    stringBuilder.append("<a href='" + changePage(i, source, country, city, theme, title, description) + "' class='" + isActive(i, page) + "'>" + (i + 1) + "</a>");
                }
            } else {
                for (int i = total - 5; i < total; i++) {
                    stringBuilder.append("<a href='" + changePage(i, source, country, city, theme, title, description) + "' class='" + isActive(i, page) + "'>" + (i + 1) + "</a>");
                }
            }
            if (page < total - 1) {
                stringBuilder.append("<a href='" + changePage(page + 1, source, country, city, theme, title, description) + "'>></a>" +
                        "<a href='" + changePage(total - 1, source, country, city, theme, title, description) + "'>尾页</a>");
            }
        }
        return stringBuilder.toString();
    }

    public static String changePage(int num, String source, String country, String city, String theme, String title, String description) {
        String url = null;
        if (source.equals("browse1")) {
            url = "browse?page=" + num + "&country=" + country + "&city=" + city +
                    "&theme=" + theme + "&submit1=筛+选";
        } else if (source.equals("browse2")) {
            url = "browse?page=" + num + "&title=" + title + "&submit2=搜索";
        } else if (source.equals("my_favourite")) {
            url = "my_favourite?page=" + num;
        } else if (source.equals("my_photo")) {
            url = "my_photos?page=" + num;
        } else if (source.equals("search1")) {
            url = "search?page=" + num + "&title=" + title + "&submit=搜索";
        } else if (source.equals("search2")) {
            url = "search?page=" + num + "&description=" + description + "&submit=搜索";
        }
        return url;
    }
}
