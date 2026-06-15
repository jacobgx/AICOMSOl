/*
 * surface_cracked_cylinder.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:10 by COMSOL 6.3.0.290. */
public class surface_cracked_cylinder {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fracture_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").insertFile("surface_cracked_cylinder_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("cmd1");

    model.param().set("p", "1[MPa]");
    model.param().descr("p", "\u538b\u529b\u8f7d\u8377");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"207[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8000"});

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2, 5, 6, 8, 9, 11, 12, 15, 22, 26);
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(11);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("crack1", "Crack", 2);
    model.component("comp1").physics("solid").feature("crack1").selection().set(6, 9);
    model.component("comp1").physics("solid").feature("crack1").set("CrackSurface", "Symmetric");
    model.component("comp1").physics("solid").feature("crack1").selection("CrackFront").set(13);
    model.component("comp1").physics("solid").feature("crack1").create("jint1", "JIntegral", 1);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4, 7, 17, 24, 27, 31);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "p");
    model.component("comp1").physics("solid").feature("crack1").create("fl1", "CrackFaceLoad", 2);
    model.component("comp1").physics("solid").feature("crack1").feature("fl1").set("p", "p");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "th/2");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "a/200");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "a/100");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "a/200");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").set("targetmesh", "morph");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 6);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("elemcount", 12);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").set("smooth", "none");
    model.result("pg1").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("vol1").set("colorcalibration", -1);
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormax", 20);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u88c2\u7eb9 (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "material");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1")
         .set("expr", new String[]{"solid.crack1.e1X*solid.crack1.crackSize ", "solid.crack1.e1Y*solid.crack1.crackSize ", "solid.crack1.e1Z*solid.crack1.crackSize "});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("scaleactive", true);
    model.result("pg2").feature("arwl1").set("scale", "1");
    model.result("pg2").feature("arwl1").label("\u88c2\u7eb9\u6269\u5c55\u65b9\u5411 (\u88c2\u7eb9 1)");
    model.result("pg2").feature("arwl1").active(false);
    model.result("pg2").create("arwl2", "ArrowLine");
    model.result("pg2").feature("arwl2")
         .set("expr", new String[]{"solid.crack1.jint1.e1X*solid.crack1.crackSize*solid.crack1.jint1.J/solid.crack1.jint1.Jmax", "solid.crack1.jint1.e1Y*solid.crack1.crackSize*solid.crack1.jint1.J/solid.crack1.jint1.Jmax", "solid.crack1.jint1.e1Z*solid.crack1.crackSize*solid.crack1.jint1.J/solid.crack1.jint1.Jmax"});
    model.result("pg2").feature("arwl2").set("placement", "elements");
    model.result("pg2").feature("arwl2").set("scaleactive", true);
    model.result("pg2").feature("arwl2").set("scale", "1");
    model.result("pg2").feature("arwl2").label("J \u79ef\u5206 1, \u8ba1\u7b97");
    model.result("pg2").feature("arwl2").create("col1", "Color");
    model.result("pg2").feature("arwl2").feature("col1").set("expr", "root.comp1.solid.crack1.jint1.J");
    model.result("pg2").create("con1", "Isosurface");
    model.result("pg2").feature("con1")
         .set("expr", "sqrt((X-solid.crack1.jint1.Xp)^2+(Y-solid.crack1.jint1.Yp)^2+(Z-solid.crack1.jint1.Zp)^2)");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", new String[]{"solid.crack1.jint1.r"});
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "gray");
    model.result("pg2").feature("con1").create("tran1", "Transparency");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").label("J \u79ef\u5206 1, \u79ef\u5206\u8def\u5f84");
    model.result("pg2").label("\u88c2\u7eb9 (solid)");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u88c2\u7eb9 1, J \u79ef\u5206 (solid)");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("jint1", "LineGraph");
    model.result("pg3").feature("jint1").selection().set(13);
    model.result("pg3").feature("jint1").set("expr", "solid.crack1.jint1.J");
    model.result("pg3").feature("jint1").set("resolution", "norefine");
    model.result("pg3").feature("jint1").label("J \u79ef\u5206 1");
    model.result("pg3").label("\u88c2\u7eb9 1, J \u79ef\u5206 (solid)");
    model.result("pg3").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().set(2, 3);
    model.component("comp1").variable("var1").set("angle", "atan2((Z-R1)/a,X/c)");
    model.component("comp1").variable("var1").descr("angle", "\u53c2\u6570\u5316\u89d2");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("jint1").set("xdata", "expr");
    model.result("pg3").feature("jint1").set("xdataexpr", "angle");
    model.result("pg3").feature("jint1").set("xdataunit", "\u00b0");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u88c2\u7eb9 1\uff0cKI");
    model.result("pg4").run();
    model.result("pg4").feature("jint1").label("KI");
    model.result("pg4").feature("jint1").set("expr", "solid.crack1.jint1.KI");
    model.result("pg4").feature("jint1")
         .set("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d");
    model.result("pg4").feature("jint1").set("expr", "solid.crack1.jint1.KI/1e6");
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("ymin", 0);
    model.result("pg4").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("surface_cracked_cylinder_results.txt");
    model.result("pg4").run();
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("linecolor", "fromtheme");
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linemarker", "asterisk");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg4").feature("jint1").set("legend", true);
    model.result("pg4").feature("jint1").set("autosolution", false);
    model.result("pg4").feature("jint1").set("autoplotlabel", true);
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("legendmethod", "manual");
    model.result("pg4").feature("tblp1").setIndex("legends", "KI\uff0c\u53c2\u8003\u503c", 0);
    model.result().evaluationGroup().create("eg_dset1solid", "EvaluationGroup");
    model.result().evaluationGroup("eg_dset1solid").label("\u65ad\u88c2\u529b\u5b66\u7ed3\u679c (solid)");
    model.result().evaluationGroup("eg_dset1solid").set("data", "dset1");
    model.result().evaluationGroup("eg_dset1solid").set("transpose", true);
    model.result().evaluationGroup("eg_dset1solid").create("jint", "EvalGlobal");
    model.result().evaluationGroup("eg_dset1solid").feature("jint").label("J \u79ef\u5206");
    model.result().evaluationGroup("eg_dset1solid").feature("jint").setIndex("expr", "solid.crack1.jint1.Jmax", 0);
    model.result().evaluationGroup("eg_dset1solid").feature("jint")
         .setIndex("descr", "\u6700\u5927 J \u79ef\u5206 [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset1solid").create("sif1", "EvalGlobal");
    model.result().evaluationGroup("eg_dset1solid").feature("sif1")
         .label("\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201c1\u201d");
    model.result().evaluationGroup("eg_dset1solid").feature("sif1").setIndex("expr", "solid.crack1.jint1.KImax", 0);
    model.result().evaluationGroup("eg_dset1solid").feature("sif1")
         .setIndex("descr", "\u6700\u5927\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset1solid").create("sif2", "EvalGlobal");
    model.result().evaluationGroup("eg_dset1solid").feature("sif2")
         .label("\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201c2\u201d");
    model.result().evaluationGroup("eg_dset1solid").feature("sif2").setIndex("expr", "solid.crack1.jint1.KIImax", 0);
    model.result().evaluationGroup("eg_dset1solid").feature("sif2")
         .setIndex("descr", "\u6700\u5927\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cII\u201d [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset1solid").create("sif3", "EvalGlobal");
    model.result().evaluationGroup("eg_dset1solid").feature("sif3")
         .label("\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201c3\u201d");
    model.result().evaluationGroup("eg_dset1solid").feature("sif3")
         .setIndex("expr", "solid.crack1.jint1.KIIImax", 0);
    model.result().evaluationGroup("eg_dset1solid").feature("sif3")
         .setIndex("descr", "\u6700\u5927\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cIII\u201d [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset1solid").label("\u65ad\u88c2\u529b\u5b66\u7ed3\u679c (solid)");
    model.result("pg1").run();

    model.title("\u5706\u67f1\u8868\u9762\u88c2\u7eb9");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u793a\u4f8b\u7814\u7a76\u5706\u67f1\u5185\u8868\u9762\u7684\u534a\u692d\u5706\u88c2\u7eb9\u3002\u5706\u67f1\u5185\u90e8\u548c\u88c2\u7eb9\u9762\u627f\u53d7\u538b\u529b\u8f7d\u8377\u3002\u5176\u4e2d\u8ba1\u7b97\u6cbf\u88c2\u7eb9\u524d\u6cbf\u7684 J \u79ef\u5206\uff0c\u7136\u540e\u5c06\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\u4e0e\u53c2\u8003\u503c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("surface_cracked_cylinder.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
