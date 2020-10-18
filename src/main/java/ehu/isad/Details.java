package ehu.isad;

import java.util.Arrays;

public class Details {
    String[] publishers;
    Integer number_of_pages;
    String title;

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTilte(){

        return this.title;

    }

    public String getPages(){

        return this.number_of_pages.toString();

    }

    public String getTitle(){

        return this.title;

    }

    public String getPublishers() {
        StringBuffer katea = new StringBuffer();
        for (int x=0;x<this.publishers.length;x++){
            katea =katea.append(this.publishers[x]+" ");
        }
        return katea.toString();
    }

}