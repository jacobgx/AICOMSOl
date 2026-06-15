/*
 * tesla_microvalve_shape_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:09 by COMSOL 6.3.0.290. */
public class tesla_microvalve_shape_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("D", "0.2[mm]");
    model.param().descr("D", "\u7279\u5f81\u957f\u5ea6");
    model.param().set("L", "5*D");
    model.param().descr("L", "\u901a\u9053\u957f\u5ea6");
    model.param().set("H", "1.75*D");
    model.param().descr("H", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("LT", "150[\u00b5m]");
    model.param().descr("LT", "\u7b2c\u4e00\u6761\u7ebf x \u548c y \u8303\u56f4");
    model.param().set("XT", "250[\u00b5m]");
    model.param().descr("XT", "\u7b2c\u4e00\u6761\u7ebf\u4f4d\u7f6e");
    model.param().set("LT2", "LT/2");
    model.param().descr("LT2", "\u7b2c\u4e8c\u6761\u7ebf x \u548c y \u8303\u56f4");
    model.param().set("XT2", "XT+LT2");
    model.param().descr("XT2", "\u7b2c\u4e8c\u6761\u7ebf\u4f4d\u7f6e");
    model.param().set("X3", "150[\u00b5m]");
    model.param().descr("X3", "\u7b2c\u4e09\u6761\u7ebf x \u4f4d\u7f6e");
    model.param().set("Y3", "H/2");
    model.param().descr("Y3", "\u7b2c\u4e09\u6761\u7ebf y \u4f4d\u7f6e");
    model.param().set("L3", "100[\u00b5m]");
    model.param().descr("L3", "\u7b2c\u4e09\u6761\u7ebf\u957f\u5ea6");
    model.param().set("phi1", "-pi/2[rad]");
    model.param().descr("phi1", "\u7b2c\u4e09\u6761\u7ebf\u65b9\u5411");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"D/2", "D/2"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-D/2", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"D/2", "D/2"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"L", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L-H+D/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "D/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H", 2, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"XT", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"XT+LT", "LT"});
    model.component("comp1").geom("geom1").feature("ls1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"XT2", "H"});
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"XT2+LT2", "H-LT2"});
    model.component("comp1").geom("geom1").feature("ls2").set("selresult", true);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new String[]{"X1+L1*cos(phi1)/2", "0"});
    model.component("comp1").geom("geom1").feature("ls3").setIndex("coord1", "Y3+L3*sin(phi1)/2", 1);
    model.component("comp1").geom("geom1").feature("ls3").setIndex("coord1", "X3+L3*cos(phi1)/2", 0);
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"X3-L3*cos(phi1)/2", "0"});
    model.component("comp1").geom("geom1").feature("ls3").setIndex("coord2", "Y3-L3*sin(phi1)/2", 1);
    model.component("comp1").geom("geom1").feature("ls3").set("selresult", true);
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pol1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u5de6");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "-D/2+1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u53f3");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "L+D/2-1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u9876\u90e8");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "H*0.999");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5185\u58c1");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"ls1", "ls2", "ls3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel2").label("\u9876\u90e8\u548c\u5e95\u90e8");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"boxsel1", "boxsel4"});
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("tesla_microvalve_shape_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
