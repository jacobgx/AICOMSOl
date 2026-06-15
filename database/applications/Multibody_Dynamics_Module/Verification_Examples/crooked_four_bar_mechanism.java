/*
 * crooked_four_bar_mechanism.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:27 by COMSOL 6.3.0.290. */
public class crooked_four_bar_mechanism {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.param().set("d", "5[mm]");
    model.param().descr("d", "\u8fde\u6746\u76f4\u5f84");
    model.param().set("l1", "0.12[m]");
    model.param().descr("l1", "\u5782\u76f4\u8fde\u6746\u957f\u5ea6");
    model.param().set("l2", "0.24[m]");
    model.param().descr("l2", "\u6c34\u5e73\u8fde\u6746\u957f\u5ea6");
    model.param().set("theta", "5[deg]");
    model.param().descr("theta", "\u504f\u79fb\u89d2");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "l1");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"l2", "0", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "l2");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "l1"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"7e10"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.33"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"3000"});

    model.component("comp1").physics("mbd").prop("ShapeProperty").set("order_displacement", 2);
    model.component("comp1").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att1").selection().set(3);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj1").set("AxisOfJointType", "SelectFromCS");
    model.component("comp1").physics("mbd").feature("hgj1").set("AxisToUse", 2);
    model.component("comp1").physics("mbd").create("att2", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att2").selection().set(4);
    model.component("comp1").physics("mbd").create("att3", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att3").selection().set(7);
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "att2");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "att3");
    model.component("comp1").physics("mbd").create("att4", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att4").selection().set(12);
    model.component("comp1").physics("mbd").create("att5", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att5").selection().set(16);
    model.component("comp1").physics("mbd").feature().duplicate("hgj3", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "att4");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "att5");
    model.component("comp1").physics("mbd").feature("hgj3").set("AxisOfJointType", "SpecifyDirection");
    model.component("comp1").physics("mbd").feature("hgj3").set("e", new String[]{"sin(theta)", "cos(theta)", "0"});
    model.component("comp1").physics("mbd").create("att6", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att6").selection().set(15);
    model.component("comp1").physics("mbd").feature().duplicate("hgj4", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "att6");
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", -1);

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("att1");
    model.nodeGroup("grp1").add("att2");
    model.nodeGroup("grp1").add("att3");
    model.nodeGroup("grp1").add("att4");
    model.nodeGroup("grp1").add("att5");
    model.nodeGroup("grp1").add("att6");
    model.nodeGroup("grp1").label("\u8fde\u63a5\u4ef6");
    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").add("hgj3");
    model.nodeGroup("grp2").add("hgj4");
    model.nodeGroup("grp2").label("\u94f0\u94fe\u5173\u8282");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3, 7, 16);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.01,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("expr", new String[]{"u", "20*v", "w"});
    model.result("pg1").run();
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"x", "Y+20*v", "z"});
    model.result("pg1").feature("pttraj1").selection().set(11);
    model.result("pg1").feature("pttraj1").set("linetype", "tube");
    model.result("pg1").feature().duplicate("pttraj2", "pttraj1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj2").selection().set(15);
    model.result("pg1").feature("pttraj2").set("linecolor", "blue");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("vB");
    model.result().table("tbl1").importData("crooked_four_bar_mechanism_vB.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("vC");
    model.result().table("tbl2").importData("crooked_four_bar_mechanism_vC.txt");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("y \u4f4d\u79fb\uff1aB \u70b9");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(4);
    model.result("pg3").feature("ptgr1").set("expr", "v");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "COMSOL", 0);
    model.result("pg3").run();
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("linemarker", "asterisk");
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u53c2\u8003\u8d44\u6599 1", 0);
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u9762\u5916\u4f4d\u79fb\uff0cB \u70b9 (m)");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("y \u4f4d\u79fb\uff1aC \u70b9");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").selection().set(13);
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("table", "tbl2");
    model.result("pg4").run();
    model.result("pg4").set("ylabel", "\u9762\u5916\u4f4d\u79fb\uff0cC \u70b9 (m)");
    model.result("pg4").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 100);
    model.result("pg1").run();

    model.title("\u542b\u88c5\u914d\u7f3a\u9677\u7684\u56db\u6746\u673a\u6784");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u67d4\u6027\u591a\u4f53\u52a8\u529b\u5b66\u57fa\u51c6\u95ee\u9898\uff0c\u7814\u7a76\u56db\u6746\u673a\u6784\uff0c\u5176\u4e2d\u4e00\u4e2a\u5173\u8282\u5177\u6709\u7f3a\u9677\u3002\u7531\u4e8e\u4ec5\u5f53\u8fde\u6746\u53ef\u53d8\u5f62\u65f6\u8be5\u673a\u6784\u624d\u80fd\u8fd0\u52a8\uff0c\u56e0\u6b64\u673a\u6784\u4e2d\u7684\u8fde\u6746\u662f\u67d4\u6027\u7684\u3002\u56db\u4e2a\u94f0\u94fe\u5173\u8282\u7528\u4e8e\u5728\u8fde\u6746\u4e4b\u95f4\u5efa\u7acb\u8fde\u63a5\u3002\u672c\u4f8b\u5c06\u5206\u6790\u5f97\u5230\u7684\u4e24\u4e2a\u4e0d\u540c\u4f4d\u7f6e\u7684\u9762\u5916\u53d8\u5f62\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("crooked_four_bar_mechanism.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
