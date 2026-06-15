/*
 * forced_vibration_of_a_composite_laminate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:32 by COMSOL 6.3.0.290. */
public class forced_vibration_of_a_composite_laminate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Dynamics_and_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/lshell", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D_11", "141.34[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 11 \u5206\u91cf");
    model.param().set("D_12", "3.35[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 12 \u5206\u91cf");
    model.param().set("D_13", "D_12", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 13 \u5206\u91cf");
    model.param().set("D_22", "10.25[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 22 \u5206\u91cf");
    model.param().set("D_23", "2.83[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 23 \u5206\u91cf");
    model.param().set("D_33", "D_22", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 33 \u5206\u91cf");
    model.param().set("D_44", "4.52[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 44 \u5206\u91cf");
    model.param().set("D_55", "2.95[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 55 \u5206\u91cf");
    model.param().set("D_66", "D_44", "\u8584\u5c42\u5f39\u6027\u77e9\u9635 66 \u5206\u91cf");
    model.param().set("rho_l", "1520[kg/m^3]", "\u8584\u5c42\u5bc6\u5ea6");
    model.param().set("th", "0.5[mm]", "\u8584\u5c42\u539a\u5ea6");
    model.param().set("F", "1[N]", "\u5355\u4f4d\u8f7d\u8377");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u6750\u6599\uff1a\u78b3\u7ea4-\u73af\u6c27");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[90/45/0]");
    model.material("lmat1").setIndex("rotation", 90, 0);
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 90, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 90, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", 90, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", 90, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("rotation", 45, 1);
    model.material("lmat1").setIndex("rotation", 0, 2);
    model.material("lmat1").set("widthRatio", 0.6);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("vel_z", "abs(lshell.u_tZ)");
    model.component("comp1").variable("var1").descr("vel_z", "\u6a2a\u5411\u901f\u5ea6\uff0c\u5e45\u503c");
    model.component("comp1").variable("var1").set("Z_z", "abs(F/lshell.u_tZ)");
    model.component("comp1").variable("var1").descr("Z_z", "\u963b\u6297\uff0c\u5927\u5c0f");

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"300[mm]", "100[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "150[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "30[mm]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"150[mm]", "200[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"100[mm]", "400[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c3", "c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3")
         .set("pos", new String[]{"200[mm]", "300[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("rot", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");

    model.component("comp1").physics("lshell").feature("lemm1").set("SolidModel", "Anisotropic");

    model.material("mat1").propertyGroup().create("Anisotropic", "Anisotropic", "Anisotropic");
    model.material("mat1").propertyGroup("Anisotropic")
         .set("D", new String[]{"D_11", "D_12", "D_22", "D_13", "D_23", "D_33", "0", "0", "0", "D_44", 
         "0", "0", "0", "0", "D_55", "0", "0", "0", "0", "0", 
         "D_66"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"rho_l"});

    model.component("comp1").physics("lshell").create("del1", "Delamination", 2);
    model.component("comp1").physics("lshell").feature("del1").selection().set(4, 5, 6);

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").physics("lshell").feature("del1").set("applyTo", "select");
    model.component("comp1").physics("lshell").feature("del1")
         .set("shelllist_lCheck", new String[]{"0", "0", "0", "0"});
    model.component("comp1").physics("lshell").feature("del1").setIndex("shelllist_lCheck", 1, 2, 0);
    model.component("comp1").physics("lshell").feature("del1").set("InitialState", "Delaminated");
    model.component("comp1").physics("lshell").feature("del1").set("pn", "1e-3*lshell.Eequ/lshell.d_ad");
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().set(1, 4, 7);
    model.component("comp1").physics("lshell").create("ll1", "LineLoad", 0);
    model.component("comp1").physics("lshell").feature("ll1").label("\u7ebf\u8f7d\u8377\uff0cA \u70b9");
    model.component("comp1").physics("lshell").feature("ll1").selection().set(19, 21, 23);
    model.component("comp1").physics("lshell").feature("ll1").set("forceType", "TotalForce");
    model.component("comp1").physics("lshell").feature("ll1").set("force", new String[]{"0", "0", "F"});
    model.component("comp1").physics("lshell").feature().duplicate("ll2", "ll1");
    model.component("comp1").physics("lshell").feature("ll2")
         .label("\u7ebf\u8f7d\u8377\uff0cB \u70b9\uff1a[\u5f2f\u66f2]");
    model.component("comp1").physics("lshell").feature("ll2").selection().set(20, 22, 24);
    model.component("comp1").physics("lshell").feature().duplicate("ll3", "ll2");
    model.component("comp1").physics("lshell").feature("ll3")
         .label("\u7ebf\u8f7d\u8377\uff0cB \u70b9\uff1a[\u626d\u8f6c]");
    model.component("comp1").physics("lshell").feature("ll3").set("force", new String[]{"0", "0", "-F"});

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u5f2f\u66f2\u8f7d\u8377");
    model.study("std1").feature("freq").set("plist", "range(1,5,501)");
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"lshell/ll3"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1lshelllshl", "LayeredMaterial");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1lshelllshl");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").label("\u5e94\u529b (lshell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"lshell.misesGp_peak"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").label("\u4e09\u7ef4\u622a\u70b9\uff1aA");
    model.result().dataset("cpt1").set("data", "dset1lshelllshl");
    model.result().dataset("cpt1").set("pointx", "0.3 0.3 0.3");
    model.result().dataset("cpt1").set("pointy", "0 0.15 0.3");
    model.result().dataset("cpt1").set("pointz", "0 0 0");
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").label("\u4e09\u7ef4\u622a\u70b9\uff1aB");
    model.result().dataset("cpt2").set("pointy", "0.1 0.25 0.4");
    model.result().dataset().duplicate("cpt3", "cpt2");
    model.result().dataset("cpt3").label("\u4e09\u7ef4\u622a\u70b9\uff1aC");
    model.result().dataset("cpt3").set("pointx", "0.25 0.25 0.25");
    model.result().dataset("cpt3").set("pointy", "0.07 0.22 0.37");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("lss1", "LayeredMaterialSlice");
    model.result("pg2").feature("lss1").set("expr", new String[]{"lshell.misesGp_peak"});
    model.result("pg2").feature("lss1").set("threshold", "manual");
    model.result("pg2").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("lss1").set("colortable", "Prism");
    model.result("pg2").feature("lss1").set("colortabletrans", "none");
    model.result("pg2").feature("lss1").set("colorscalemode", "linear");
    model.result("pg2").feature("lss1").set("locdef", "relative");
    model.result("pg2").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg2").feature("lss1").create("def", "Deform");
    model.result("pg2").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").create("thr1", "ThroughThickness");
    model.result("pg3").feature("thr1").set("expr", new String[]{"lshell.misesGp_peak"});
    model.result("pg3").feature("thr1").set("legend", true);
    model.result("pg3").feature("thr1").set("posentry", "selection");
    model.result("pg3").feature("thr1").selection().geom("geom1", 0);
    model.result("pg3").feature("thr1").selection().set(1);
    model.result("pg3").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg3").setIndex("looplevelindices", 101, 0);
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result("pg3").feature("thr1").selection().set(2, 4, 6);
    model.result("pg3").feature("thr1").set("includeinterfaces", "all");
    model.result("pg3").feature("thr1").set("legendmethod", "manual");
    model.result("pg3").feature("thr1").setIndex("legends", "\u7b2c\u4e00\u79cd\u60c5\u51b5", 0);
    model.result("pg3").feature("thr1").setIndex("legends", "\u7b2c\u4e8c\u79cd\u60c5\u51b5", 1);
    model.result("pg3").feature("thr1").setIndex("legends", "\u7b2c\u4e09\u79cd\u60c5\u51b5", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u963b\u6297\uff0cA \u70b9 [\u5f2f\u66f2\u8f7d\u8377]");
    model.result("pg4").set("data", "cpt1");
    model.result("pg4").set("ylog", true);
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").set("expr", "Z_z");
    model.result("pg4").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u7b2c\u4e00\u79cd\u60c5\u51b5", 0);
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u7b2c\u4e8c\u79cd\u60c5\u51b5", 1);
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u7b2c\u4e09\u79cd\u60c5\u51b5", 2);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u963b\u6297\uff0cB \u70b9 [\u5f2f\u66f2\u8f7d\u8377]");
    model.result("pg5").set("data", "cpt2");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u901f\u5ea6\uff0cC \u70b9 [\u5f2f\u66f2\u8f7d\u8377]");
    model.result("pg6").set("data", "cpt3");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("expr", "vel_z");
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
    model.result().export("anim1").label("\u52a8\u753b [\u5f2f\u66f2\u8f7d\u8377]");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("showframe", 25);
    model.result().export("anim1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/lshell", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u626d\u8f6c\u8f7d\u8377");
    model.study("std2").feature("freq").set("plist", "range(1,5,501)");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"lshell/ll2"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset2lshelllshl", "LayeredMaterial");
    model.result().dataset("dset2lshelllshl").set("data", "dset2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2lshelllshl");
    model.result("pg7").setIndex("looplevel", 101, 0);
    model.result("pg7").label("\u5e94\u529b (lshell) 1");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"lshell.misesGp_peak"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").feature("surf1").create("def", "Deform");
    model.result("pg7").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg7").run();
    model.result().dataset().duplicate("cpt4", "cpt1");
    model.result().dataset("cpt4").set("data", "dset2lshelllshl");
    model.result().dataset().duplicate("cpt5", "cpt2");
    model.result().dataset("cpt5").set("data", "dset2lshelllshl");
    model.result().dataset().duplicate("cpt6", "cpt3");
    model.result().dataset("cpt6").set("data", "dset2lshelllshl");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 101, 0);
    model.result("pg8").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell) 1");
    model.result("pg8").set("showlegends", true);
    model.result("pg8").create("lss1", "LayeredMaterialSlice");
    model.result("pg8").feature("lss1").set("expr", new String[]{"lshell.misesGp_peak"});
    model.result("pg8").feature("lss1").set("threshold", "manual");
    model.result("pg8").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("lss1").set("colortable", "Prism");
    model.result("pg8").feature("lss1").set("colortabletrans", "none");
    model.result("pg8").feature("lss1").set("colorscalemode", "linear");
    model.result("pg8").feature("lss1").set("locdef", "relative");
    model.result("pg8").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg8").feature("lss1").create("def", "Deform");
    model.result("pg8").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg8").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg8").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell) 1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").set("showlegends", true);
    model.result("pg9").create("thr1", "ThroughThickness");
    model.result("pg9").feature("thr1").set("expr", new String[]{"lshell.misesGp_peak"});
    model.result("pg9").feature("thr1").set("legend", true);
    model.result("pg9").feature("thr1").set("posentry", "selection");
    model.result("pg9").feature("thr1").selection().geom("geom1", 0);
    model.result("pg9").feature("thr1").selection().set(1);
    model.result("pg9").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell) 1");
    model.result("pg9").setIndex("looplevelinput", "last", 0);
    model.result("pg9").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell) 1");
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg9").setIndex("looplevelindices", 101, 0);
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").run();
    model.result("pg9").feature("thr1").selection().set(2, 4, 6);
    model.result("pg9").feature("thr1").set("includeinterfaces", "all");
    model.result("pg9").run();
    model.result("pg4").run();
    model.result().duplicate("pg10", "pg4");
    model.result("pg10").run();
    model.result("pg10").label("\u963b\u6297\uff0cA \u70b9 [\u626d\u8f6c\u8f7d\u8377]");
    model.result("pg10").set("data", "cpt4");
    model.result("pg10").run();
    model.result("pg5").run();
    model.result().duplicate("pg11", "pg5");
    model.result("pg11").run();
    model.result("pg11").label("\u963b\u6297\uff0cB \u70b9 [\u626d\u8f6c\u8f7d\u8377]");
    model.result("pg11").set("data", "cpt5");
    model.result("pg11").run();
    model.result("pg6").run();
    model.result().duplicate("pg12", "pg6");
    model.result("pg12").run();
    model.result("pg12").label("\u901f\u5ea6\uff0cC \u70b9 [\u626d\u8f6c\u8f7d\u8377]");
    model.result("pg12").set("data", "cpt6");
    model.result("pg12").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("plotgroup", "pg6");
    model.nodeGroup("grp1").label("\u5f2f\u66f2\u8f7d\u8377");

    model.result("pg7").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("plotgroup", "pg11");
    model.nodeGroup("grp2").add("plotgroup", "pg12");
    model.nodeGroup("grp2").label("\u626d\u8f6c\u8f7d\u8377");

    model.result().export("anim1").showFrame();
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u52a8\u753b [\u626d\u8f6c\u8f7d\u8377]");
    model.result().export("anim2").set("plotgroup", "pg7");
    model.result().export("anim2").run();
    model.result("pg2").run();

    model.title("\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u7684\u5f3a\u8feb\u632f\u52a8\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u627f\u53d7\u5f2f\u626d\u8f7d\u8377\u7684\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u8fdb\u884c\u5f3a\u8feb\u632f\u52a8\u5206\u6790\uff0c\u8fd9\u79cd\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u7531\u78b3-\u73af\u6c27\u6811\u8102\u6750\u6599\u5236\u6210\uff0c\u5e76\u5305\u542b\u4e09\u5c42\u89d2\u94fa\u8bbe\u5c42\u3002\n\n\u8be5\u6a21\u578b\u8ba1\u7b97\u4e0d\u540c\u4f4d\u7f6e\u7684\u7ed3\u6784\u963b\u6297\u968f\u9891\u7387\u7684\u53d8\u5316\u60c5\u51b5\uff0c\u5e76\u5206\u6790\u4e0d\u540c\u7c7b\u578b\u7684\u8131\u5c42\u5bf9\u7ed3\u6784\u963b\u6297\u7684\u5f71\u54cd\u3002\u5176\u4e2d\u4f7f\u7528\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u5efa\u6a21\u7684\u201c\u5206\u5c42\u201d(LW) \u7406\u8bba\u5bf9\u8131\u5c42\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("forced_vibration_of_a_composite_laminate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
