/*
 * district_heating_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:57 by COMSOL 6.3.0.290. */
public class district_heating_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W", "25[m]", "x \u957f\u5ea6");
    model.param().set("L", "40[m]", "y \u957f\u5ea6");
    model.param().set("Lz", "10[m]", "\u6696\u6c14\u7247\u957f\u5ea6");
    model.param().set("nxBlock", "4", "\u6bcf\u8857\u533a\u7684\u7528\u6237\u6570");
    model.param().set("nyBlock", "4", "\u6bcf\u8857\u533a\u7684\u7528\u6237\u6570");
    model.param().set("nxBlocks", "5", "\u8857\u533a\u6570");
    model.param().set("nyBlocks", "6", "\u8857\u533a\u6570");
    model.param().set("producer1x", "0[m]", "\u5236\u70ed\u8bbe\u5907 1 x \u5750\u6807");
    model.param().set("producer1y", "0[m]", "\u5236\u70ed\u8bbe\u5907 1 y \u5750\u6807");
    model.param().set("producer2x", "L*nxBlock*ceil(nxBlocks/2)", "\u5236\u70ed\u8bbe\u5907 2 x \u5750\u6807");
    model.param().set("producer2y", "W*nyBlock*nyBlocks", "\u5236\u70ed\u8bbe\u5907 2 y \u5750\u6807");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").label("\u957f\u65b9\u4f53");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "closed");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "nyBlock*W", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "nxBlock*L", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "nyBlock*W", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "nxBlock*L", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 2);
    model.component("comp1").geom("geom1").feature("pol1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("pol1");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "Lz");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").label("\u7528\u6237 1");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "L/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "L/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "Lz", 1, 2);
    model.component("comp1").geom("geom1").feature("pol2").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("pol3", "pol2");
    model.component("comp1").geom("geom1").feature("pol3").label("\u7528\u6237 2");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "1.5*W", 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "1.5*W", 1, 1);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("pol2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"nxBlock", "2", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"L", "nyBlock*W", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").named("pol3");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"2", "nyBlock-2", "1"});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"nxBlock*L", "W", "0"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u957f\u65b9\u4f53+\u7528\u6237");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"pol1", "pol2", "pol3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"pol1", "pol2", "pol3"});
    model.component("comp1").geom("geom1").runPre("arr3");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").feature("arr3")
         .set("fullsize", new String[]{"nxBlocks", "nyBlocks", "1"});
    model.component("comp1").geom("geom1").feature("arr3").set("displ", new String[]{"nxBlock*L", "nyBlock*W", "0"});
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").feature().duplicate("pol4", "pol3");
    model.component("comp1").geom("geom1").feature("pol4").label("\u5165\u53e3 1 \u7ebf");
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "producer1x", 0, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "producer1y", 0, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "producer1x", 1, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "producer1y", 1, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "Lz/3", 1, 2);
    model.component("comp1").geom("geom1").feature().duplicate("pol5", "pol4");
    model.component("comp1").geom("geom1").feature("pol5").label("\u51fa\u53e3 1 \u7ebf");
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "Lz", 0, 2);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "2*Lz/3", 1, 2);
    model.component("comp1").geom("geom1").feature().duplicate("pol6", "pol5");
    model.component("comp1").geom("geom1").feature("pol6").label("\u51fa\u53e3 2 \u7ebf");
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", "producer2x", 0, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", "producer2y", 0, 1);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", "producer2x", 1, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", "producer2y", 1, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pol7", "pol6");
    model.component("comp1").geom("geom1").feature("pol7").label("\u5165\u53e3 2 \u7ebf");
    model.component("comp1").geom("geom1").feature("pol7").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol7").setIndex("table", "Lz/3", 1, 2);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").label("\u5165\u53e3 \uff11");
    model.component("comp1").geom("geom1").feature("ballsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("ballsel1").set("posx", "producer1x");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posy", "producer1y");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posz", "Lz/3");
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", "Lz/100");
    model.component("comp1").geom("geom1").feature("ballsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("ballsel2", "ballsel1");
    model.component("comp1").geom("geom1").feature("ballsel2").label("\u51fa\u53e3 1");
    model.component("comp1").geom("geom1").feature("ballsel2").set("posz", "2*Lz/3");
    model.component("comp1").geom("geom1").feature().duplicate("ballsel3", "ballsel2");
    model.component("comp1").geom("geom1").feature("ballsel3").label("\u51fa\u53e3 2");
    model.component("comp1").geom("geom1").feature("ballsel3").set("posx", "producer2x");
    model.component("comp1").geom("geom1").feature("ballsel3").set("posy", "producer2y");
    model.component("comp1").geom("geom1").feature().duplicate("ballsel4", "ballsel3");
    model.component("comp1").geom("geom1").feature("ballsel4").label("\u5165\u53e3 \uff12");
    model.component("comp1").geom("geom1").feature("ballsel4").set("posz", "Lz/3");
    model.component("comp1").geom("geom1").run("ballsel4");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u7528\u6237");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"pol2", "pol3"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u70ed\u6d41");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "Lz/100");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u51b7\u6d41");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "Lz*99/100");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u7528\u6237\u70b9");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 0);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel2"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel3", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u7528\u6237\u70ed\u70b9");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel3").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel3").set("input", new String[]{"pol2", "pol3"});
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u5165\u53e3+\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel3")
         .set("input", new String[]{"pol4", "pol5", "pol6", "pol7"});

    model.title(null);

    model.description("");

    model.label("district_heating_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
