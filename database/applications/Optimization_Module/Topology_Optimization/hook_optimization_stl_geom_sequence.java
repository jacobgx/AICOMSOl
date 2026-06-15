/*
 * hook_optimization_stl_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:47 by COMSOL 6.3.0.290. */
public class hook_optimization_stl_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

    model.param().set("r1", "7.5[mm]");
    model.param().descr("r1", "\u5b54\u534a\u5f84");
    model.param().set("r2", "20[mm]");
    model.param().descr("r2", "\u6700\u5927\u4e0a\u534a\u5f84");
    model.param().set("r3", "50[mm]");
    model.param().descr("r3", "\u6700\u5927\u4e0b\u534a\u5f84");
    model.param().set("r4", "25[mm]");
    model.param().descr("r4", "\u6700\u5c0f\u4e0b\u534a\u5f84");
    model.param().set("Lc", "90[mm]");
    model.param().descr("Lc", "\u5b54\u5230\u94a9\u4e2d\u5fc3\u7684\u8ddd\u79bb");
    model.param().set("w1", "2.5[mm]");
    model.param().descr("w1", "\u8f7d\u8377\u8868\u9762\u5bbd\u5ea6");
    model.param().set("w2", "12.5[mm]");
    model.param().descr("w2", "\u603b\u5bbd\u5ea6");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "2*r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", "2*r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq3", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq3").set("size", "2*r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq3")
         .set("pos", new String[]{"-r3", "-r3-Lc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq4", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq4").set("size", "2*r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq4")
         .set("pos", new String[]{"-r4", "-r4-Lc"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"r3-r4", "r3"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-r3", "-Lc+(r3-r4)/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input")
         .set("sq2", "sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2")
         .set("r1", "sq1", "sq4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard1").selection("domain").set("dif1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pard1").selection("vertexsegment")
         .set("dif1", 16, 18);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pard1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("pard1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("del1", 7, 8, 9, 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point")
         .set("fil1", 2, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "(r3-r4)/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil3", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil3").selection("point")
         .set("fil2", 4, 14);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil3").set("radius", "r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil4", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil4").selection("point")
         .set("fil3", 6, 13);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil4").set("radius", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil5", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil5").selection("point")
         .set("fil4", 1, 17);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil5").set("radius", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex1").set("fil5", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex2").set("fil5", 16);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").selection("vertex1").set("fil5", 13);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").selection("vertex2").set("fil5", 18);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("fil5", "ls1", "ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del2").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del2").selection("input").set("csol1", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del3", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del3").selection("input")
         .set("del2", 2, 7);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil6", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil6").selection("point")
         .set("del3", 4, 13);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil6").set("radius", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil7", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil7").selection("point").set("fil6", 17);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil7").set("radius", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil8", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil8").selection("point").set("fil7", 21);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil8").set("radius", "r4+2*r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "w1", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "w2", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2*r1");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "w2");
    model.component("comp1").geom("geom1").feature("cyl1").set("selresult", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u94a9\u548c\u56fa\u5b9a\u57df");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"ext1", "cyl1"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").named("ext1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").named("cyl1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2")
         .label("\u8981\u5220\u9664\u7684\u5185\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "w1*0.99");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "w1*1.01");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("boxsel2");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").label("\u5b54");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "r1*1.005");
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "inside");

    model.component("comp1").selection().create("cyl1", "Cylinder");

    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("cylsel2", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel2").label("\u7b2c\u4e00\u8f7d\u8377\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("cylsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel2").set("r", "r4*1.001");
    model.component("comp1").geom("geom1").feature("cylsel2").set("top", "w1*1.001");
    model.component("comp1").geom("geom1").feature("cylsel2").set("pos", new String[]{"0", "-Lc", "0"});
    model.component("comp1").geom("geom1").feature("cylsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("cylsel2");
    model.component("comp1").geom("geom1").create("cylsel3", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel3").label("\u7b2c\u4e8c\u8f7d\u8377\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("cylsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel3").set("r", "(r3-r4)/2*1.001");
    model.component("comp1").geom("geom1").feature("cylsel3").set("top", "w1*1.001");
    model.component("comp1").geom("geom1").feature("cylsel3").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("cylsel3").set("pos", new String[]{"-(r3+r4)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cylsel3").setIndex("pos", "-Lc", 1);
    model.component("comp1").geom("geom1").feature("cylsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("cylsel3");
    model.component("comp1").geom("geom1").create("cylsel4", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel4").label("\u56fa\u5b9a\u57df");
    model.component("comp1").geom("geom1").feature("cylsel4").set("r", "r1*1.001");
    model.component("comp1").geom("geom1").run("cylsel4");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u8f7d\u8377\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"cylsel2", "cylsel3"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 3);
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u4f18\u5316\u7684\u57df");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"cylsel4"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u81ea\u7531\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"boxsel1", "unisel2"});
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("hook_optimization_stl_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
