/*
 * modular_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:14 by COMSOL 6.3.0.290. */
public class modular_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("H", "0.0805[m]");
    model.param().descr("H", "\u5bb9\u5668\u9ad8\u5ea6");
    model.param().set("T", "H");
    model.param().descr("T", "\u5bb9\u5668\u76f4\u5f84");
    model.param().set("C", "1/3*H");
    model.param().descr("C", "\u95f4\u9699");
    model.param().set("B", "4");
    model.param().descr("B", "\u6321\u677f\u6570");
    model.param().set("bw", "T/10");
    model.param().descr("bw", "\u6321\u677f\u5bbd\u5ea6");
    model.param().set("Da", "1/3*T");
    model.param().descr("Da", "\u53f6\u8f6e\u76f4\u5f84");
    model.param().set("shaft_diameter", "1/10*Da");
    model.param().descr("shaft_diameter", "\u8f74\u5f84");
    model.param().set("blade_length", "Da/4");
    model.param().descr("blade_length", "Rushton \u6da1\u8f6e\u53f6\u7247\u957f\u5ea6");
    model.param().set("blade_width", "Da/5");
    model.param().descr("blade_width", "\u53f6\u8f6e\u53f6\u7247\u5bbd\u5ea6");

    model.geom()
         .load(new String[]{"part1"}, "Mixer_Module\\Impellers,_Radial\\rushton_impeller.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_hu", "shaft_diameter+Da/20");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_ib", "blade_length");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_ib", "blade_width");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_id", "Da-2*(blade_length*3/4)");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_im", "Da");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hp_im", "-blade_width/2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_is", "shaft_diameter");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u53f6\u8f6e\u57df");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel4", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u65cb\u8f6c\u5185\u58c1");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u65cb\u8f6c\u58c1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel3");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u79fb\u9664\u8fb9");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoedg", "pi1_sel1", "csel4");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", true);
    model.geom().load(new String[]{"part2"}, "Mixer_Module\\Shafts\\impeller_shaft.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hp_im", "-blade_width/2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_is", "shaft_diameter");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_is", "H-C+blade_width");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoobj", "pi2_csel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_csel1.bnd", "csel3");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoedg", "pi2_sel1", "csel4");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pi1", "pi2");
    model.component("comp1").geom("geom1").feature("uni1").set("repairtoltype", "relative");
    model.geom().load(new String[]{"part3"}, "Mixer_Module\\Tanks\\flat_bottom_tank.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").set("rot", 90);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "n_ba", "B");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "w_ba", "bw");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_im", "Da");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_ta", "T");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "h_ta", "H");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "hp_ta", "-C");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "rf_ta", 0);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("\u5185\u58c1");
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("\u89c6\u56fe\u6291\u5236");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel1.bnd", "csel5");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel4.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel7.bnd", "csel7");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u6d41\u4f53\u57df");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("pi3_csel5");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").feature("dif1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("dif1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5e73\u538b\u70b9");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("dif1", 34);
    model.component("comp1").geom("geom1").selection().create("csel8", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel8").label("\u538b\u529b\u70b9\u7ea6\u675f");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel8");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u8981\u79fb\u9664\u7684\u8fb9");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("dif1", 9, 61, 78, 79);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("dif1", 2);
    model.component("comp1").geom("geom1").feature("sel3").label("\u65cb\u8f6c\u6d41\u4f53\u57df");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").named("csel4");
    model.component("comp1").geom("geom1").feature("ige1").set("ignorevtx", false);
    model.component("comp1").geom("geom1").run("ige1");

    model.title("\u6a21\u5757\u5316\u5c42\u6d41\u6405\u62cc\u5668 - \u6a21\u677f\u6587\u4ef6");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u7528\u4e8e\u5206\u6790\u201c\u6a21\u5757\u5316\u6405\u62cc\u5668\u201d\u6a21\u578b\u7684\u5c42\u6d41\u60c5\u51b5\u3002\u51e0\u4f55\u4f53\u4e3a Rushton \u53f6\u8f6e\u4e0e\u5e73\u5e95\u91dc\u7684\u7ec4\u5408\u7ed3\u6784\u3002\u5176\u4e2d\u4ece\u201c\u96f6\u4ef6\u5e93\u201d\u5bfc\u5165\u7528\u4e8e\u6784\u5efa\u53f6\u8f6e\u548c\u5bb9\u5668\u7684\u51e0\u4f55\u5b50\u5e8f\u5217\u3002");

    model.label("modular_mixer_geom.mph");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"972"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1"});
    model.component("comp1").material("mat1").label("\u7845\u6cb9 Si1000");

    model.component("comp1").common("rot1").selection().named("geom1_sel3");
    model.component("comp1").common("rot1").set("revolutionsPerTime", 40);

    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("spf").create("iwbc2", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc2").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().named("geom1_csel8_pnt");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("tau_riw", "x*(spf.T_trac_uy+spf.T_trac_dy)-y*(spf.T_trac_ux+spf.T_trac_dx)");
    model.component("comp1").variable("var1")
         .descr("tau_riw", "\u5355\u4f4d\u9762\u79ef\u626d\u77e9\uff08\u5185\u58c1\uff09");
    model.component("comp1").variable("var1").set("tau_rw", "x*(spf.T_tracy)-y*(spf.T_tracx)");
    model.component("comp1").variable("var1")
         .descr("tau_rw", "\u5355\u4f4d\u9762\u79ef\u626d\u77e9\uff08\u65cb\u8f6c\u58c1\uff09");
    model.component("comp1").variable("var1").set("P_riw", "tau_riw*rot1.alphat");
    model.component("comp1").variable("var1")
         .descr("P_riw", "\u5355\u4f4d\u9762\u79ef\u529f\u8017\uff08\u65cb\u8f6c\u5185\u58c1\uff09");
    model.component("comp1").variable("var1").set("P_rw", "tau_rw*rot1.alphat");
    model.component("comp1").variable("var1")
         .descr("P_rw", "\u5355\u4f4d\u9762\u79ef\u529f\u8017\uff08\u65cb\u8f6c\u58c1\uff09");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel2_bnd");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_csel3_bnd");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u6240\u6709\u58c1");
    model.result().dataset("surf1").set("data", "none");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1");
    model.result().dataset("surf2").set("data", "none");
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("\u5185\u58c1");
    model.result().dataset("surf3").set("data", "none");
    model.result().dataset("surf2").set("data", "dset1");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection().set(2, 3, 4, 17, 18, 19, 20, 21, 22, 24, 34);
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 33, 34, 35, 36, 37, 38);
    model.result().dataset("surf3").set("data", "dset1");
    model.result().dataset("surf3").selection().geom("geom1", 2);
    model.result().dataset("surf3").selection().set(1, 13, 14, 15, 16, 23, 33, 35, 36, 37, 38);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("data", "surf2");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "surf2");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").feature().create("slit1", "SurfaceSlit");
    model.result("pg2").feature("slit1").set("data", "surf3");
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("upexpr", "up(p)");
    model.result("pg2").feature("slit1").set("downexpr", "down(p)");
    model.result("pg2").feature("slit1").set("titletype", "none");
    model.result("pg2").feature("slit1").set("smooth", "internal");
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("data", "surf3");
    model.result("pg2").feature("slit1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset().create("ps1", "ParSurface");
    model.result().dataset("ps1").set("parmax1", "2*pi");
    model.result().dataset("ps1").set("parmin2", "-Da/10");
    model.result().dataset("ps1").set("parmax2", "Da/10");
    model.result().dataset("ps1").set("exprx", "0.186*T*cos(s1)");
    model.result().dataset("ps1").set("expry", "0.186*T*sin(s1)");
    model.result().dataset("ps1").set("exprz", "s2");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6\uff1a\u5927\u5c0f\u548c\u77e2\u91cf");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature().remove("tran1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("slit1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "cpl1");
    model.result("pg2").feature("surf2").set("colortable", "RainbowLight");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("data", "cpl2");
    model.result("pg2").feature("arws1").set("expr", new String[]{"0", "v", "w"});
    model.result("pg2").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", 0.005);
    model.result("pg2").feature("arws1").set("arrowcount", 1000);
    model.result("pg2").feature("arws1").set("color", "white");

    model.component("comp1").view().create("view5", "geom1");
    model.component("comp1").view("view5").hideEntities().create("hide1");
    model.component("comp1").view("view5").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view5").hideEntities("hide1").named("geom1_csel7_bnd");
    model.component("comp1").view("view5").set("locked", true);

    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u901f\u5ea6\uff1a\u5927\u5c0f\u548c\u9762\u5185\u77e2\u91cf");
    model.result("pg2").set("view", "view5");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u626d\u77e9");
    model.result().numerical("gev1").setIndex("expr", "abs(intop1(tau_riw)+intop2(tau_rw))", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u626d\u77e9");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u529f\u8017");
    model.result().numerical("gev2").setIndex("expr", "abs(intop1(P_riw)+intop2(P_rw))", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u529f\u8017");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u6d41\u6570");
    model.result().numerical("int1").set("data", "ps1");
    model.result().numerical("int1").setIndex("expr", "(u*cos(s1)+v*sin(s1))/40[1/s]/Da^3", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u6d41\u6570");
    model.result().numerical("int1").set("table", "tbl3");
    model.result().numerical("int1").setResult();
    model.result("pg2").run();

    model.title("\u6a21\u5757\u5316\u6405\u62cc\u5668");

    model
         .description("\u672c\u4f8b\u57fa\u4e8e\u4e00\u4e2a\u542b\u6709\u51e0\u4f55\u96f6\u4ef6\u7684\u51e0\u4f55\u6a21\u578b\u6587\u4ef6\u8fdb\u884c\u6784\u5efa\uff0c\u8fd9\u4e9b\u51e0\u4f55\u96f6\u4ef6\u7528\u4e8e\u6784\u5efa\u5e26\u659c\u53f6\u7247\u53f6\u8f6e\u6216 Rushton  \u6da1\u8f6e\u7684\u6321\u677f\u5f0f\u5e73\u5e95\u6405\u62cc\u5668\u548c\u789f\u5f62\u5e95\u6405\u62cc\u5668\u3002\u8be5\u6a21\u578b\u4e3b\u8981\u6a21\u62df\u5e26 Rushton \u6da1\u8f6e\u7684\u5e73\u5e95\u5bb9\u5668\u4e2d\u7684\u5c42\u6d41\u6df7\u5408\u60c5\u51b5\u3002\n\n\u76f8\u5173\u793a\u4f8b\u4f7f\u7528\u201c\u51bb\u7ed3\u8f6c\u5b50\u201d\u4eff\u771f\u65b9\u6cd5\uff0c\u65cb\u8f6c\u90e8\u4ef6\u3001\u5bb9\u5668\u58c1\u548c\u6321\u677f\u5747\u4fdd\u6301\u5728\u539f\u4f4d\uff0c\u901a\u8fc7\u5f15\u5165\u79bb\u5fc3\u529b\u548c\u79d1\u91cc\u5965\u5229\u529b\u6765\u5206\u6790\u65cb\u8f6c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("modular_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
