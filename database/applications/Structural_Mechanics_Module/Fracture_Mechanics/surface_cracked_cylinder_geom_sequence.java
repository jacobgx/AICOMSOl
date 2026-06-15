/*
 * surface_cracked_cylinder_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:10 by COMSOL 6.3.0.290. */
public class surface_cracked_cylinder_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fracture_Mechanics");

    model.param().set("R1", "1[m]");
    model.param().descr("R1", "\u5185\u534a\u5f84");
    model.param().set("R2", "1.1[m]");
    model.param().descr("R2", "\u5916\u534a\u5f84");
    model.param().set("th", "R2-R1");
    model.param().descr("th", "\u539a\u5ea6");
    model.param().set("L", "5[m]");
    model.param().descr("L", "\u5706\u67f1\u4f53\u957f\u5ea6");
    model.param().set("a", "th*0.5");
    model.param().descr("a", "\u88c2\u7eb9\u692d\u5706\u5c0f\u8f74");
    model.param().set("c", "a/0.4");
    model.param().descr("c", "\u88c2\u7eb9\u692d\u5706\u957f\u8f74");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "th", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("selresultshow", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("top", "L/2");
    model.component("comp1").geom("geom1").feature("cylsel1").set("bottom", 0);
    model.component("comp1").geom("geom1").feature("cylsel1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cylsel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("cylsel1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").set("selresultshow", false);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5e76\u96c6");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxes", new String[]{"c", "a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("pos", new String[]{"0", "R1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("e1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "c", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "R1", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "c", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "R1-c", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "R1+a", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "-a", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", "R1+a", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("e1", 2, 3);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zy");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "-a");
    model.component("comp1").geom("geom1").feature("wp2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp2").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp2").set("quickorigin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp2").selection("originvertex").set("wp1", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "a*0.3");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pard1").selection("domain").set("c1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pard1").selection("vertexsegment")
         .set("c1", 1, 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pard1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", true);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").set("wp2", 1, 2);
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").named("wp1");
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", true);
    model.component("comp1").geom("geom1").feature("swe1").set("reversedir", true);
    model.component("comp1").geom("geom1").feature("swe1").set("spineperpstart", true);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("swe1").selection("diredge").set("wp1", new int[]{3});
    model.component("comp1").geom("geom1").feature("swe1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("swe1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("swe1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").named("cyl1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").named("swe1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "-a*0.2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del2").selection("input").named("comsel1");
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel2").set("input", new String[]{"swe1"});
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "-th");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "th");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "R2*tan(10[deg])");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "R2-th");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "R2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", 0.3);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "transformed");
    model.component("comp1").geom("geom1").feature("wp4").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("wp4").set("transaxistype", "x");
    model.component("comp1").geom("geom1").feature("wp4").set("transrot", -10);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").named("cyl1");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par2").set("workplane", "wp3");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("par3", "Partition");
    model.component("comp1").geom("geom1").feature("par3").selection("input").named("cyl1");
    model.component("comp1").geom("geom1").feature("par3").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input").named("swe1");
    model.component("comp1").geom("geom1").run("cmd1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cmd1");

    model.title(null);

    model.description("");

    model.label("surface_cracked_cylinder_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
