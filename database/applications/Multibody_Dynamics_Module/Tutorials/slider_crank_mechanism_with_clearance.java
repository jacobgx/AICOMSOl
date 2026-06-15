/*
 * slider_crank_mechanism_with_clearance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:25 by COMSOL 6.3.0.290. */
public class slider_crank_mechanism_with_clearance {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m1", "0.3[kg]", "\u66f2\u67c4\u7684\u8d28\u91cf");
    model.param().set("m2", "0.21[kg]", "\u8fde\u6746\u7684\u8d28\u91cf");
    model.param().set("m3", "0.14[kg]", "\u6ed1\u5757\u7684\u8d28\u91cf");
    model.param().set("I1", "0.00001[kg*m^2]", "\u66f2\u67c4\u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("I2", "0.00025[kg*m^2]", "\u8fde\u6746\u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("C", "0.0005[m]", "\u8f74\u9888\u4e0e\u8f74\u627f\u4e4b\u95f4\u7684\u95f4\u9699");
    model.param().set("omega", "5000*2*pi[rad]/60[s]", "\u66f2\u67c4\u7684\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "slider_crank_mechanism_with_clearance.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 1);
    model.component("comp1").geom("geom1").feature("sel1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("sel1")
         .set("customcolor", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").feature().duplicate("sel2", "sel1");
    model.component("comp1").geom("geom1").feature("sel2").label("\u66f2\u67c4");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 2, 3);
    model.component("comp1").geom("geom1").feature("sel2")
         .set("customcolor", new double[]{0.8196078538894653, 1, 0.10980392247438431});
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").feature().duplicate("sel3", "sel2");
    model.component("comp1").geom("geom1").feature("sel3").label("\u8fde\u6746");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 4, 5, 6);
    model.component("comp1").geom("geom1").feature("sel3")
         .set("customcolor", new double[]{1, 0.501960813999176, 0.501960813999176});
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").feature().duplicate("sel4", "sel3");
    model.component("comp1").geom("geom1").feature("sel4").label("\u6ed1\u5757");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 7);
    model.component("comp1").geom("geom1").feature("sel4").set("customcolor", new double[]{0, 1, 1});
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u652f\u67b6\u548c\u66f2\u67c4");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u8fde\u6746\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel3"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u8f74\u9888\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 39, 40, 41, 47, 48);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u65e0\u8f74\u9888\u8fde\u6746");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel5"});

    model.component("comp1").pair().create("p3", "Identity");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").pair("p3").source().named("geom1_sel5");
    model.component("comp1").pair("p3").source().set(39, 40, 47, 48);
    model.component("comp1").pair("p3").destination().set(54, 55);

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "5e-4[s]");
    model.component("comp1").func("step1").set("smooth", "1e-3");

    model.component("comp1").physics("mbd").prop("AutoModeling").set("MassMI", true);
    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createRigidDomains");
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u652f\u67b6");
    model.component("comp1").physics("mbd").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u66f2\u67c4");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("mt", "m1");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1")
         .set("mi", new String[]{"I1", "0", "0", "0", "I1", "0", "0", "0", "I1"});
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u8fde\u6746");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("mt", "m2");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1")
         .set("mi", new String[]{"I2", "0", "0", "0", "I2", "0", "0", "0", "I2"});
    model.component("comp1").physics("mbd").feature("rd4").label("\u521a\u6027\u6750\u6599\uff1a\u6ed1\u5757");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("mt", "m3");
    model.component("comp1").physics("mbd").feature("rd4").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd4").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("rd4").feature("pdr1").setIndex("Direction", true, 2);
    model.component("comp1").physics("mbd").feature("rd4").feature("pdr1")
         .set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd4").feature("pdr1").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("mbd").feature("rd4").feature("pdr1").setIndex("ConstrainedRotation", true, 1);
    model.component("comp1").physics("mbd").feature("rd4").feature("pdr1").setIndex("ConstrainedRotation", true, 2);
    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createJoints");
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", "omega*step1(t)");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("WeakConstraints", true);
    model.component("comp1").physics("mbd").prop("Results").set("ReferenceFrame", "rd4");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "tri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u94f0\u94fe\u5173\u8282");
    model.study("std1").feature("time").set("tlist", "range(0,0.00001,0.025)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj1_pm1_RM")
         .set("scaleval", "1e8*(0.1*0.22463859864235272)^3*1000");
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

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

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb\uff1a\u94f0\u94fe\u5173\u8282");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6\uff1a\u94f0\u94fe\u5173\u8282");
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u76f8\u5bf9\u4f4d\u79fb\uff1a\u94f0\u94fe\u5173\u8282");
    model.result("pg3").set("view", "new");
    model.result("pg3").set("frametype", "material");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbd.disp_ref");
    model.result("pg3").feature("surf1")
         .set("descr", "\u4f4d\u79fb\u5927\u5c0f\uff0c\u53c2\u8003\u5750\u6807\u7cfb");
    model.result("pg3").feature("surf1").set("colortable", "Spectrum");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("expr", new String[]{"u_ref", "v_ref", "w_ref"});
    model.result("pg3").feature("surf1").feature("def1")
         .set("descr", "\u4f4d\u79fb\u573a\uff0c\u53c2\u8003\u5750\u6807\u7cfb \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_sel5");
    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "mbd.disp_ref");
    model.result("pg3").feature("con1").set("titletype", "none");
    model.result("pg3").feature("con1").set("colortable", "Spectrum");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("con1").feature("def1").set("expr", new String[]{"u_ref", "v_ref", "w_ref"});
    model.result("pg3").feature("con1").feature("def1")
         .set("descr", "\u4f4d\u79fb\u573a\uff0c\u53c2\u8003\u5750\u6807\u7cfb \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg3").feature("con1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("con1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature("con1").create("sel1", "Selection");
    model.result("pg3").feature("con1").feature("sel1").selection().named("geom1_sel5");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("expr", "dom");
    model.result("pg3").feature("surf2").set("descr", "\u5b9e\u4f53\u7d22\u5f15");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "custom");
    model.result("pg3").feature("surf2").set("customcolor", new double[]{1, 0.501960813999176, 0.501960813999176});
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("sel1").selection().named("geom1_difsel1");
    model.result("pg3").run();
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", "dom");
    model.result("pg3").feature("vol1").set("descr", "\u5b9e\u4f53\u7d22\u5f15");
    model.result("pg3").feature("vol1").set("titletype", "none");
    model.result("pg3").feature("vol1").set("coloring", "uniform");
    model.result("pg3").feature("vol1").set("color", "cyan");
    model.result("pg3").feature("vol1").create("sel1", "Selection");
    model.result("pg3").feature("vol1").feature("sel1").selection().named("geom1_sel4");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("tran1").set("transparency", 0.75);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("def1").set("expr", new String[]{"u_ref", "v_ref", "w_ref"});
    model.result("pg3").feature("vol1").feature("def1")
         .set("descr", "\u4f4d\u79fb\u573a\uff0c\u53c2\u8003\u5750\u6807\u7cfb \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg3").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("vol2", "vol1");
    model.result("pg3").run();
    model.result("pg3").feature("vol2").set("coloring", "colortable");
    model.result("pg3").feature("vol2").set("colortable", "TrafficLight");
    model.result("pg3").feature("vol2").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").feature("vol2").feature("sel1").selection().set();
    model.result("pg3").feature("vol2").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg3").run();
    model.result("pg3").feature("vol2").feature().remove("tran1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").physics("mbd").create("crj1", "ClearanceJoint", -1);
    model.component("comp1").physics("mbd").feature("crj1").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("crj1").set("SourcePoint", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("crj1").feature("spb1").selection().named("geom1_sel5");
    model.component("comp1").physics("mbd").feature("crj1").feature("spb1").selection().set(39, 40, 47, 48);
    model.component("comp1").physics("mbd").feature("crj1").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("crj1").set("c", "C");
    model.component("comp1").physics("mbd").feature("crj1").set("pj", "mbd.crj1.Eequ*(0.1*mbd.diag)/100");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u95f4\u9699\u5173\u8282");
    model.study("std2").feature("time").set("tlist", "range(0,0.00001,0.025)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"mbd/hgj3"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_mbd_hgj1_pm1_RM")
         .set("scaleval", "1e8*(0.1*0.22463859864235272)^3*1000");
    model.sol("sol2").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4f4d\u79fb (mbd)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 2501, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u901f\u5ea6 (mbd)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 2501, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg5").feature("vol1").set("descractive", true);
    model.result("pg5").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg5").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg5").feature("vol1").set("rangecoloractive", "on");
    model.result("pg5").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg5").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg5").feature("vol1").set("colortable", "Cyclic");
    model.result("pg5").feature("vol1").set("colorlegend", false);
    model.result("pg5").feature("vol1").set("colortabletype", "discrete");
    model.result("pg5").feature("vol1").set("smooth", "none");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("def1", "Deform");
    model.result("pg5").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg5").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature().create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg5").feature("arwl1").set("placement", "elements");
    model.result("pg5").feature("arwl1").set("data", "parent");
    model.result("pg5").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg5").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg5").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb\uff1a\u95f4\u9699\u5173\u8282");
    model.result("pg5").run();
    model.result("pg5").label("\u901f\u5ea6\uff1a\u95f4\u9699\u5173\u8282");
    model.result("pg3").run();
    model.result().duplicate("pg6", "pg3");
    model.result("pg6").run();
    model.result("pg6").label("\u76f8\u5bf9\u4f4d\u79fb\uff1a\u95f4\u9699\u5173\u8282");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6ed1\u5757\u901f\u5ea6");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.rd4.u_tx"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u521a\u4f53\u901f\u5ea6\uff0cx \u5206\u91cf"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"m/s"});
    model.result("pg7").feature("glob1").setIndex("descr", "\u94f0\u94fe\u5173\u8282", 0);
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("data", "dset2");
    model.result("pg7").feature("glob2").setIndex("descr", "\u95f4\u9699\u5173\u8282", 0);
    model.result("pg7").run();
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u521a\u4f53\u901f\u5ea6 (m/s)");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u6ed1\u5757\u52a0\u901f\u5ea6");
    model.result("pg8").set("ylabel", "\u521a\u4f53\u52a0\u901f\u5ea6 (m/s<sup>2</sup>)");
    model.result("pg8").set("legendpos", "upperright");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.rd4.u_ttx", 0);
    model.result("pg8").run();
    model.result("pg8").feature("glob2").setIndex("expr", "mbd.rd4.u_ttx", 0);
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u53cd\u4f5c\u7528\u529b\u77e9");
    model.result("pg9").set("ylabel", "\u53cd\u4f5c\u7528\u529b\u77e9 (N*m)");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("expr", "mbd.hgj1.pm1.RM", 0);
    model.result("pg9").run();
    model.result("pg9").feature("glob2").setIndex("expr", "mbd.hgj1.pm1.RM", 0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u8f74\u9888\u8f68\u8ff9\uff1a\u95f4\u9699\u5173\u8282");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("view", "new");
    model.result("pg10").set("edges", false);
    model.result("pg10").create("pttraj1", "PointTrajectories");
    model.result("pg10").feature("pttraj1").set("plotdata", "global");
    model.result("pg10").feature("pttraj1").set("expr", new String[]{"mbd.crj1.dx", "mbd.crj1.dy", "mbd.crj1.dz"});
    model.result("pg10").feature("pttraj1").set("linetype", "tube");
    model.result("pg10").feature("pttraj1").set("radiusexpr", "1e-8");
    model.result("pg10").feature("pttraj1").set("tuberadiusscaleactive", true);
    model.result("pg10").feature("pttraj1").set("tuberadiusscale", 500);
    model.result("pg10").feature("pttraj1").create("col1", "Color");
    model.result("pg10").run();
    model.result("pg10").feature("pttraj1").feature("col1").set("expr", "t");
    model.result("pg10").feature("pttraj1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg10").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result().dataset().create("pc1", "ParCurve3D");
    model.result().dataset("pc1").set("data", "dset2");
    model.result().dataset("pc1").set("parmax1", "2*pi");
    model.result().dataset("pc1").set("exprx", "C*sin(s)");
    model.result().dataset("pc1").set("exprz", "C*cos(s)");
    model.result("pg10").run();
    model.result("pg10").create("line1", "Line");
    model.result("pg10").feature("line1").set("data", "pc1");
    model.result("pg10").feature("line1").set("expr", "1");
    model.result("pg10").feature("line1").set("linetype", "tube");
    model.result("pg10").feature("line1").set("radiusexpr", "1e-8");
    model.result("pg10").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg10").feature("line1").set("tuberadiusscale", 500);
    model.result("pg10").feature("line1").set("coloring", "uniform");
    model.result("pg10").feature("line1").set("color", "magenta");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u5173\u8282\u529b\uff1a\u95f4\u9699\u5173\u8282");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("showlegends", false);
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"mbd.crj1.Fj"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u5173\u8282\u529b\u5927\u5c0f"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg11").run();
    model.result().create("pg12", "PolarGroup");
    model.result("pg12").run();
    model.result("pg12").label("\u95f4\u9699\u8ddd\u79bb\uff1a\u95f4\u9699\u5173\u8282");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("showgrid", false);
    model.result("pg12").set("showlegends", false);
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("linewidth", "preference");
    model.result("pg12").feature("glob1").set("expr", new String[]{"mbd.crj1.gap"});
    model.result("pg12").feature("glob1").set("descr", new String[]{"\u95f4\u9699\u8ddd\u79bb"});
    model.result("pg12").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg12").feature("glob1").set("xdata", "expr");
    model.result("pg12").feature("glob1").set("xdataexpr", "mbd.hgj1.th");
    model.result("pg12").feature("glob1").set("linewidth", 2);
    model.result("pg12").feature().duplicate("glob2", "glob1");
    model.result("pg12").run();
    model.result("pg12").feature("glob2").setIndex("expr", 0, 0);
    model.result("pg12").feature("glob2").set("linestyle", "dotted");
    model.result("pg12").feature("glob2").set("linecolor", "magenta");
    model.result("pg12").run();
    model.result("pg12").feature("glob1").create("col1", "Color");
    model.result("pg12").run();
    model.result("pg12").feature("glob1").feature("col1").set("expr", "t");
    model.result("pg12").feature("glob1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg12").run();
    model.result("pg6").run();
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
    model.result().export("anim1").label("\u4f4d\u79fb\uff1a\u94f0\u94fe\u5173\u8282");
    model.result().export("anim1").set("maxframes", 50);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u4f4d\u79fb\uff1a\u95f4\u9699\u5173\u8282");
    model.result().export("anim2").set("plotgroup", "pg4");
    model.result().export("anim1").showFrame();
    model.result().export().duplicate("anim3", "anim1");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").label("\u76f8\u5bf9\u4f4d\u79fb\uff1a\u94f0\u94fe\u5173\u8282");
    model.result().export("anim3").set("plotgroup", "pg3");
    model.result().export().duplicate("anim4", "anim3");
    model.result().export("anim4").showFrame();
    model.result().export("anim4").label("\u76f8\u5bf9\u4f4d\u79fb\uff1a\u95f4\u9699\u5173\u8282");
    model.result().export("anim4").set("plotgroup", "pg6");

    return model;
  }

  public static Model run2(Model model) {
    model.result().export().duplicate("anim5", "anim4");
    model.result().export("anim5").showFrame();
    model.result().export("anim5").label("\u8f74\u9888\u8f68\u8ff9\uff1a\u95f4\u9699\u5173\u8282");
    model.result().export("anim5").set("plotgroup", "pg10");

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"mbd/crj1"});

    model.result("pg6").run();

    model.title("\u5177\u6709\u5173\u8282\u95f4\u9699\u7684\u66f2\u67c4\u6ed1\u5757\u673a\u6784");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u5177\u6709\u95f4\u9699\u5173\u8282\u7684\u66f2\u67c4\u6ed1\u5757\u673a\u6784\u7684\u52a8\u529b\u5b66\u7279\u6027\u3002\u5176\u4e2d\u5047\u8bbe\u8be5\u673a\u6784\u7684\u6240\u6709\u90e8\u4ef6\u90fd\u662f\u521a\u6027\u7684\uff0c\u652f\u67b6\u3001\u66f2\u67c4\u3001\u8fde\u6746\u7528\u4e24\u4e2a\u94f0\u94fe\u8fdb\u884c\u8fde\u63a5\u3002\u4e3a\u4e86\u5bf9\u8fde\u6746\u4e0e\u6ed1\u5757\u4e4b\u95f4\u7684\u5173\u8282\u8fdb\u884c\u5efa\u6a21\uff0c\u672c\u4f8b\u8003\u8651\u4e86\u4e24\u79cd\u4e0d\u540c\u7684\u60c5\u51b5\u3002\u7b2c\u4e00\u79cd\u60c5\u51b5\u5047\u8bbe\u8fde\u6746-\u6ed1\u5757\u5173\u8282\u662f\u4e00\u4e2a\u7406\u60f3\u7684\u94f0\u94fe\uff0c\u6ca1\u6709\u4efb\u4f55\u95f4\u9699\u3002\u7b2c\u4e8c\u79cd\u60c5\u51b5\u4f7f\u7528\u95f4\u9699\u5173\u8282\uff0c\u5141\u8bb8\u8fde\u63a5\u90e8\u4ef6\u5728\u63d0\u4f9b\u7684\u95f4\u9699\u8ddd\u79bb\u5185\u8fd0\u52a8\u3002\u901a\u8fc7\u6267\u884c\u77ac\u6001\u5206\u6790\uff0c\u7814\u7a76\u4e86\u5173\u8282\u95f4\u9699\u5bf9\u6ed1\u5757\u901f\u5ea6\u3001\u6ed1\u5757\u52a0\u901f\u5ea6\u548c\u66f2\u67c4\u529b\u77e9\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("slider_crank_mechanism_with_clearance.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
