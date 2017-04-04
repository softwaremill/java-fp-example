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

        @Override
        public String toString() {
            return "empty document";
        }
    }

    final class Fake implements Document {
        private static final String BASE_URI = "localhost";
        private final String content;

        Fake(String content) {
            this.content = content;
        }

        @Override
        public Element head() throws Exception {
            Attributes attributes = new Attributes();
            attributes.put("property", "og:image");
            attributes.put("content", content);
            return new Element(Tag.valueOf("meta"), BASE_URI, attributes);
        }

        @Override
        public String toString() {
            return String.format("fake document with og:image content: %s", content);
        }
    }
}
