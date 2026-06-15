/*
 * polyaxial_screw_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class polyaxial_screw_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").inputParam().set("thread_l", "11[mm]");
    model.geom("part1").inputParam().descr("thread_l", "Screw thread length");
    model.geom("part1").inputParam().set("thread_r", "1.75[mm]");
    model.geom("part1").inputParam().descr("thread_r", "Screw stread radius");
    model.geom("part1").inputParam().set("sagittal_angle", "10[deg]");
    model.geom("part1").inputParam().descr("sagittal_angle", "Sagittal angle");
    model.geom("part1").inputParam().set("transverse_angle", "0[deg]");
    model.geom("part1").inputParam().descr("transverse_angle", "Transverse angle");
    model.geom("part1").inputParam().set("head_r", "3.5[mm]");
    model.geom("part1").inputParam().descr("head_r", "Screw head radius");
    model.geom("part1").inputParam().set("head_h", "8[mm]");
    model.geom("part1").inputParam().descr("head_h", "Screw head height");
    model.geom("part1").inputParam().set("hole_d", "3.5[mm]");
    model.geom("part1").inputParam().descr("hole_d", "Screw head hole diameter");
    model.geom("part1").run("");
    model.geom("part1").nodeGroup().create("grp1");
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").nodeGroup("grp1").add("wp1");
    model.geom("part1").feature("wp1").set("quickplane", "yz");
    model.geom("part1").feature("wp1").set("showworkplane", false);
    model.geom("part1").feature("wp1").geom().create("pol1", "Polygon");
    model.geom("part1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", "-thread_r", 1, 0);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", "-thread_r", 2, 0);
    model.geom("part1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "11*thread_l/10-(thread_r/(tan(60[deg])))", 2, 1);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 3, 0);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", "11*thread_l/10", 3, 1);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 4, 0);
    model.geom("part1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 4, 1);
    model.geom("part1").feature("wp1").geom().run("pol1");
    model.geom("part1").feature("wp1").geom().create("fil1", "Fillet");
    model.geom("part1").feature("wp1").geom().feature("fil1").selection("pointinsketch").set("pol1", 2);
    model.geom("part1").feature("wp1").geom().feature("fil1").set("radius", "2*thread_r/3");
    model.geom("part1").feature("wp1").geom().run("fil1");
    model.geom("part1").run("wp1");
    model.geom("part1").feature().create("rev1", "Revolve");
    model.geom("part1").feature("rev1").set("angtype", "full");
    model.geom("part1").feature("rev1").set("origfaces", false);
    model.geom("part1").selection().create("csel1", "CumulativeSelection");
    model.geom("part1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.geom("part1").feature("rev1").set("contributeto", "csel1");
    model.geom("part1").run("rev1");
    model.geom("part1").create("pt1", "Point");
    model.geom("part1").nodeGroup("grp1").remove("pt1", false);
    model.geom("part1").feature("pt1").setIndex("p", "thread_l/10+3*thread_r/4", 2);
    model.geom("part1").feature("pt1").setAttribute("construction", "on");
    model.geom("part1").run("pt1");

    model.view("view1").set("transparency", true);

    model.geom("part1").feature("pt1").set("selresult", true);
    model.geom("part1").feature("pt1").set("selresultshow", false);
    model.geom("part1").nodeGroup("grp1").add("pt1");
    model.geom("part1").create("cm1", "CentroidMeasurement");
    model.geom("part1").feature("cm1").selection("ent").named("pt1");
    model.geom("part1").run("cm1");
    model.geom("part1").create("rot1", "Rotate");
    model.geom("part1").feature("rot1").selection("input").set("rev1");
    model.geom("part1").feature("rot1").selection("input").named("csel1");
    model.geom("part1").feature("rot1").set("axistype", "y");
    model.geom("part1").feature("rot1").set("rot", "transverse_angle");
    model.geom("part1").feature("rot1").set("pos", new String[]{"part1.cm1.x", "0", "0"});
    model.geom("part1").feature("rot1").setIndex("pos", "part1.cm1.y", 1);
    model.geom("part1").feature("rot1").setIndex("pos", "part1.cm1.z", 2);
    model.geom("part1").run("rot1");
    model.geom("part1").create("rot2", "Rotate");
    model.geom("part1").feature("rot2").selection("input").named("csel1");
    model.geom("part1").feature("rot2").set("axistype", "x");
    model.geom("part1").feature("rot2").set("rot", "-sagittal_angle");
    model.geom("part1").feature("rot2").set("pos", new String[]{"part1.cm1.x", "0", "0"});
    model.geom("part1").feature("rot2").setIndex("pos", "part1.cm1.y", 1);
    model.geom("part1").feature("rot2").setIndex("pos", "part1.cm1.z", 2);
    model.geom("part1").run("rot2");
    model.geom("part1").run("rot2");
    model.geom("part1").nodeGroup().create("grp2");
    model.geom("part1").create("wp2", "WorkPlane");
    model.geom("part1").feature("wp2").set("unite", true);
    model.geom("part1").nodeGroup("grp1").remove("wp2", false);
    model.geom("part1").nodeGroup("grp2").add("wp2");
    model.geom("part1").feature("wp2").set("quickplane", "yz");
    model.geom("part1").feature("wp2").set("quickorigin", "vertexproj");
    model.geom("part1").feature("wp2").selection("originvertex").set("pt1", 1);
    model.geom("part1").feature("wp2").set("showworkplane", false);
    model.geom("part1").feature("wp2").geom().create("r1", "Rectangle");
    model.geom("part1").feature("wp2").geom().feature("r1").set("size", new String[]{"head_r", "head_h"});
    model.geom("part1").feature("wp2").geom().feature("r1").set("pos", new String[]{"0", "-head_h"});
    model.geom("part1").feature("wp2").geom().run("r1");
    model.geom("part1").feature("wp2").geom().create("fil1", "Fillet");
    model.geom("part1").feature("wp2").geom().feature("fil1").selection("pointinsketch").set("r1", 3);
    model.geom("part1").feature("wp2").geom().feature("fil1").set("radius", "head_r-head_r/10");
    model.geom("part1").feature("wp2").geom().run("fil1");
    model.geom("part1").run("wp2");
    model.geom("part1").feature().create("rev2", "Revolve");
    model.geom("part1").feature("rev2").set("angtype", "full");
    model.geom("part1").feature("rev2").set("origfaces", false);
    model.geom("part1").feature("rev2").set("contributeto", "csel1");
    model.geom("part1").run("rev2");
    model.geom("part1").create("cm2", "CentroidMeasurement");
    model.geom("part1").feature("cm2").selection("ent").set("rev2", 4, 5);
    model.geom("part1").run("cm2");
    model.geom("part1").create("pt2", "Point");
    model.geom("part1").feature("pt2").setIndex("p", "-head_r-head_r/3", 1);
    model.geom("part1").feature("pt2").setIndex("p", "part1.cm2.z", 2);
    model.geom("part1").selection().create("csel2", "CumulativeSelection");
    model.geom("part1").selection("csel2").label("\u7d2f\u79ef\u9009\u62e9 2");
    model.geom("part1").feature("pt2").set("contributeto", "csel2");
    model.geom("part1").run("pt2");
    model.geom("part1").create("pt3", "Point");
    model.geom("part1").feature("pt3").setIndex("p", "head_r+head_r/3", 1);
    model.geom("part1").feature("pt3").setIndex("p", "part1.cm2.z", 2);
    model.geom("part1").feature("pt3").set("contributeto", "csel2");
    model.geom("part1").run("pt3");
    model.geom("part1").create("cyl1", "Cylinder");
    model.geom("part1").feature("cyl1").set("r", "hole_d/2");
    model.geom("part1").feature("cyl1").set("h", "head_r*3");
    model.geom("part1").feature("cyl1").set("pos", new String[]{"0", "-head_r-head_r/3", "0"});
    model.geom("part1").feature("cyl1").setIndex("pos", "part1.cm2.z", 2);
    model.geom("part1").feature("cyl1").set("axistype", "y");
    model.geom("part1").feature("cyl1").set("selresult", true);
    model.geom("part1").feature("cyl1").set("selresultshow", false);
    model.geom("part1").run("cyl1");
    model.geom("part1").create("dif1", "Difference");
    model.geom("part1").feature("dif1").selection("input").named("csel1");
    model.geom("part1").feature("dif1").selection("input2").named("cyl1");
    model.geom("part1").run("dif1");
    model.geom("part1").create("uni1", "Union");
    model.geom("part1").nodeGroup("grp2").remove("uni1", false);
    model.geom("part1").feature("uni1").selection("input").named("csel1");
    model.geom("part1").feature("uni1").set("intbnd", false);
    model.geom("part1").run("uni1");
    model.geom("part1").create("wp3", "WorkPlane");
    model.geom("part1").feature("wp3").set("unite", true);
    model.geom("part1").feature("wp3").set("quickplane", "yz");
    model.geom("part1").feature("wp3").set("quickorigin", "vertexproj");
    model.geom("part1").feature("wp3").selection("originvertex").named("pt1");
    model.geom("part1").run("wp3");

    model.view("view1").set("transparency", false);

    model.title(null);

    model.description("");

    model.label("polyaxial_screw_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
