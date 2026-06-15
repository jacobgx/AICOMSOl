/*
 * differential_pumping_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:18 by COMSOL 6.3.0.290. */
public class differential_pumping_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.3);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.45);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.35, 0, -0.225});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.125);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 0.7);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0.35, -0.35, 0});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 0.125);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 0.075);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new double[]{0.35, 0, -0.3});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", 0.065);
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 0.4);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new double[]{-0.05, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl4").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", 0.125);
    model.component("comp1").geom("geom1").feature("cyl5").set("h", 0.35);
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new double[]{0.35, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl5").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("cyl1", "cyl2", "cyl3", "cyl4", "cyl5");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cyl6", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl6").set("r", 0.065);
    model.component("comp1").geom("geom1").feature("cyl6").set("h", 0.04);
    model.component("comp1").geom("geom1").feature("cyl6").set("pos", new double[]{-0.1, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl6").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl6");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("r", "20[mm]");
    model.component("comp1").geom("geom1").feature("cone1").set("h", "27[mm]");
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", "15.5[mm]");
    model.component("comp1").geom("geom1").feature("cone1").set("pos", new double[]{-0.08, 0, -0.09});
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("cone1", "cyl6");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("cyl7", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl7").set("r", 0.015);
    model.component("comp1").geom("geom1").feature("cyl7").set("h", 0.02);
    model.component("comp1").geom("geom1").feature("cyl7").set("pos", new double[]{-0.08, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl7").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl7");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl7");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl8", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl8").set("r", "1.5[mm]");
    model.component("comp1").geom("geom1").feature("cyl8").set("h", 0.03);
    model.component("comp1").geom("geom1").feature("cyl8").set("pos", new double[]{-0.08, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl8").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl8");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("cyl8", "dif1", "uni1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(1)", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(2)", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(3)", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u8154\u5ba4\u57df");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 3);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u8154\u5ba4\u548c\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 19);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u71c3\u70e7\u5ba4");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel2"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 9);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u7ba1");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").add("fin", 11, 12);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u6cf5");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 25);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u7aef\u53e3");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 35);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "-0.1[mm]");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", 0.1);
    model.component("comp1").geom("geom1").feature("cylsel1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").run("cylsel1");
    model.component("comp1").geom("geom1").create("cylsel2", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel2").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("cylsel2").set("pos", new double[]{0.35, 0, 0});
    model.component("comp1").geom("geom1").feature("cylsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("cylsel2");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u540e\u5904\u7406");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"cylsel1", "cylsel2"});

    model.title(null);

    model.description("");

    model.label("differential_pumping_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
