package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public interface Document {
    Element head() throws Exception;

    final class Empty implements Document {
        @Override
        public Element head() throws Exception {
            return new Element("meta");
        }
    }

    final class Fake implements Document {
        private final Element head;

        Fake(String content) {
            Attributes attributes = new Attributes();
            attributes.put("property", "og:image");
            attributes.put("content", content);
            this.head = new Element(Tag.valueOf("meta"), "localhost", attributes);
        }

        @Override
        public Element head() throws Exception {
            return head;
        }
    }
}
