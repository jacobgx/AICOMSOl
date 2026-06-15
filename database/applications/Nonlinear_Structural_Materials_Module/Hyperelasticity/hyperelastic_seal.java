/*
 * hyperelastic_seal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:31 by COMSOL 6.3.0.290. */
public class hyperelastic_seal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{18, 12});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{-6, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 1, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 6);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("pointinsketch").set("fil1", 4, 5);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", 4);
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").set("fil2");
    model.component("comp1").geom("geom1").feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("thi1").set("upthick", 1.5);
    model.component("comp1").geom("geom1").run("thi1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").label("\u538b\u5934");
    model.component("comp1").geom("geom1").feature("c1").set("r", 12);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{4, 24});
    model.component("comp1").geom("geom1").feature("c1").set("rot", -135);
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("c1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{20, 1});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{-7, -1});
    model.component("comp1").geom("geom1").feature("r2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r2").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u521a\u6027\u5e95\u5ea7");
    model.component("comp1").geom("geom1").feature("r2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{1, 12});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{13, 0});
    model.component("comp1").geom("geom1").feature("r3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("ccur1").selection("input").set("c1", "r2", "r3");
    model.component("comp1").geom("geom1").run("ccur1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("ccur1", 1, 2, 4, 5, 6, 8, 9, 10);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5bc6\u5c01\u6761\u5185\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .add("fin", 5, 6, 8, 12, 13, 15, 16);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5bc6\u5c01\u6761\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .add("fin", 4, 7, 9, 10, 11, 14, 17);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u80f6\u5408\u5bc6\u5c01\u6761\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 4);
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("thi1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u5bc6\u5c01\u6761");

    model.param().set("para", "0[mm]");
    model.param().descr("para", "\u5782\u76f4\u4f4d\u79fb\u53c2\u6570");
    model.param().set("d", "50[mm]");
    model.param().descr("d", "\u9762\u5916\u539a\u5ea6");

    model.component("comp1").pair().create("p1", "Contact");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").pair("p1").pairName("upper");
    model.component("comp1").pair("p1").source().named("geom1_c1_bnd");
    model.component("comp1").pair("p1").destination().named("geom1_sel2");
    model.component("comp1").pair().create("p2", "Contact");
    model.component("comp1").pair("p2").pairName("lower");
    model.component("comp1").pair("p2").source().named("geom1_csel1_bnd");
    model.component("comp1").pair("p2").destination().named("geom1_sel2");

    model.component("comp1").common().create("pres1", "PrescribedDeformation");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").selection().geom("geom1", 1);
    model.component("comp1").common("pres1").selection().named("geom1_c1_bnd");
    model.component("comp1").common("pres1").set("prescribedDeformation", new String[]{"0", "-para", "0"});

    model.component("comp1").physics("solid").prop("d").set("d", "d");
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").selection().all();
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "MooneyRivlin");
    model.component("comp1").physics("solid").feature("hmm1").set("kappa", "1e4[MPa]");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.3);
    model.component("comp1").physics("solid").feature("dcnt1").create("adh1", "Adhesion", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("adh1")
         .set("ActivationCriterion", "UserDefined");
    model.component("comp1").physics("solid").feature("dcnt1").feature("adh1").set("activation", "dom==4");
    model.component("comp1").physics("solid").feature("dcnt1").feature("adh1").set("StiffnessInput", "UserDefined");
    model.component("comp1").physics("solid").feature("dcnt1").feature("adh1")
         .set("kPerArea", new String[]{"1e10[N/m^3]", "2e10[N/m^3]", "0"});
    model.component("comp1").physics("solid").create("enc1", "EnclosedCavity", 1);
    model.component("comp1").physics("solid").feature("enc1").selection().named("geom1_sel1");

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("solid").feature("enc1").feature("fl1").set("loadGroup", "lg1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("MooneyRivlin", "MooneyRivlin", "Mooney-Rivlin[Material_parameters]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C10", new String[]{"0.37[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C01", new String[]{"0.11[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1100[kg/m^3]"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().named("geom1_csel1_bnd");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().named("geom1_c1_bnd");
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").feature("fq1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size2").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "1e-3 range(0.1,0.1,4)", 0);
    model.study("std1").feature("stat").setIndex("punit", "mm", 0);
    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u65e0\u538b\u529b", 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u6709\u538b\u529b", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 0);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u52a8\u7f51\u683c");
    model.result("pg2").create("mesh1", "Mesh");
    model.result("pg2").feature("mesh1").set("meshdomain", "surface");
    model.result("pg2").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg2").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg2").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg2").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg2").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg2").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg2").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"faceload", "\u9762\u8f7d\u8377", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"solid.enc1.fax", "solid.enc1.fay"});
    model.result("pg1").feature("arwl1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg1").feature("arwl1").set("titletype", "none");
    model.result("pg1").feature("arwl1").set("arrowcount", 300);
    model.result("pg1").feature("arwl1").set("arrowbase", "head");
    model.result("pg1").feature("arwl1").set("inheritplot", "surf1");
    model.result("pg1").feature("arwl1").set("inheritcolor", false);
    model.result("pg1").feature("arwl1").set("inheritrange", false);
    model.result("pg1").run();

    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-3");
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pw").set("scaleval", "1e5");
    model.sol("sol1").feature("s1").feature("dDef").set("linsolver", "pardiso");

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").stepFirst(1);
    model.result("pg1").run();
    model.result("pg1").stepLast(1);
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg3").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg3").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("arrowbase", "head");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"solid.dcnt1.Tnx", "solid.dcnt1.Tny"});
    model.result("pg3").feature("arwl1").set("placement", "gausspoints");
    model.result("pg3").feature("arwl1").set("gporder", 4);
    model.result("pg3").feature("arwl1").label("\u63a5\u89e6 1, \u538b\u529b");
    model.result("pg3").feature("arwl1").set("inheritplot", "none");
    model.result("pg3").feature("arwl1").set("color", "green");
    model.result("pg3").feature("arwl1").create("col", "Color");
    model.result("pg3").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg3").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg3").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg3").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("arwl1").feature("col").set("topcolor", "green");
    model.result("pg3").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg3").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg3").feature().move("surf1", 1);
    model.result("pg3").set("legendpos", "rightdouble");
    model.result("pg3").create("arwl2", "ArrowLine");
    model.result("pg3").feature("arwl2").set("arrowbase", "tail");
    model.result("pg3").feature("arwl2").set("expr", new String[]{"solid.dcnt1.Ttx", "solid.dcnt1.Tty"});
    model.result("pg3").feature("arwl2").set("placement", "gausspoints");
    model.result("pg3").feature("arwl2").set("gporder", 4);
    model.result("pg3").feature("arwl2").label("\u63a5\u89e6 1, \u6469\u64e6\u529b");
    model.result("pg3").feature("arwl2").set("inheritplot", "none");
    model.result("pg3").feature("arwl2").set("color", "magenta");
    model.result("pg3").feature("arwl2").create("col", "Color");
    model.result("pg3").feature("arwl2").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("arwl2").feature("col").set("colortabletrans", "none");
    model.result("pg3").feature("arwl2").feature("col").set("colorscalemode", "linear");
    model.result("pg3").feature("arwl2").feature("col").set("colordata", "arrowlength");
    model.result("pg3").feature("arwl2").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("arwl2").feature("col").set("topcolor", "magenta");
    model.result("pg3").feature("arwl2").feature("col").set("bottomcolor", "custom");
    model.result("pg3").feature("arwl2").feature("col")
         .set("custombottomcolor", new double[]{0.54902, 0.509804, 0.54902});
    model.result("pg3").feature().move("surf1", 2);
    model.result("pg3").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("arwl1").feature("col").set("unit", "kPa");
    model.result("pg3").run();
    model.result("pg3").feature("arwl2").feature("col").set("unit", "kPa");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u63a5\u89e6\u538b\u529b");
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg4").setIndex("looplevelindices", "range(10,5,40)", 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u63a5\u89e6\u538b\u529b\u5206\u5e03");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(7, 11, 17);
    model.result("pg4").feature("lngr1").set("expr", "solid.Tn");
    model.result("pg4").feature("lngr1").set("descr", "\u63a5\u89e6\u538b\u529b");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autosolution", false);
    model.result("pg4").feature("lngr1").set("legendprefix", "eval(para) mm");
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "last", 1);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u538b\u7f29\u529b vs. \u538b\u75d5");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "-solid.dcnt1.T_toty_upper/d", 0);
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("xlabel", "\u538b\u75d5 (mm)");
    model.result("pg5").set("ylabel", "\u529b (N/mm)");
    model.result("pg5").set("legendpos", "upperleft");

    model.title("\u8d85\u5f39\u6027\u5bc6\u5c01\u6761");

    model
         .description("\u672c\u4f8b\u4e2d\uff0c\u4e00\u4e2a\u8d85\u5f39\u6027\u6750\u6599\u6a21\u578b\u53d1\u751f\u51e0\u4f55\u5927\u53d8\u5f62\uff0c\u7531\u6b64\u5bf9\u5bc6\u5c01\u6761\u8fdb\u884c\u5efa\u6a21\u3002\u6a21\u578b\u6bd4\u8f83\u4e86\u6709/\u65e0\u5185\u538b\u65f6\u7684\u521a\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("hyperelastic_seal.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
