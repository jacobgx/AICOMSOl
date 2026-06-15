/*
 * resistive_wafer_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:59 by COMSOL 6.3.0.290. */
public class resistive_wafer_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 60);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 2);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 48);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 8.808);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{0, 0, 2});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("r", 48);
    model.component("comp1").geom("geom1").feature("cone1").set("h", 9.192);
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", 92);
    model.component("comp1").geom("geom1").feature("cone1").set("pos", new double[]{0, 0, 10.808});
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 92);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 20);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new int[]{0, 0, 20});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u6676\u7247\u8868\u9762");
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 45);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{7.844, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{0, 45});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").selection("edge").set("c1", 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").set("position", "projection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").selection("vertexproj")
         .set("r1", 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pare1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", 41);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{6.8, 20.4});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new double[]{-36.04, -10.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection("csel1").label("\u6676\u7247");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new double[]{54.4, 40.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new double[]{-29.24, -17});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new double[]{40.8, 6.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new double[]{-22.44, 23.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("size", new double[]{6.8, 34});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("pos", new double[]{25.16, -17});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("size", new double[]{47.6, 6.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new double[]{-22.44, -23.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("size", new double[]{34, 6.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("pos", new double[]{-15.64, -30.6});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input").set("fin", 31);
    model.component("comp1").geom("geom1").run("igv1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9633\u6781");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("igv1", 6);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u9634\u6781");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("igv1", 15, 17);

    model.title(null);

    model.description("");

    model.label("resistive_wafer_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
