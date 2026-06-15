/*
 * single_edge_crack.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:10 by COMSOL 6.3.0.290. */
public class single_edge_crack {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fracture_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Xa", "0.6[m]", "\u88c2\u7eb9\u957f\u5ea6");
    model.param().set("da", "0.01[m]", "\u88c2\u7eb9\u957f\u5ea6\u589e\u91cf");
    model.param().set("Wp", "1.5[m]", "\u677f\u5bbd");
    model.param().set("Hp", "1.5*Wp", "\u677f\u534a\u957f");
    model.param().set("Th", "10[mm]", "\u677f\u539a");
    model.param().set("Q0", "20[MPa]", "\u8f7d\u8377\u5f3a\u5ea6");
    model.param().set("AP", "1.4E-11", "Paris \u5b9a\u5f8b\u7cfb\u6570\uff08\u5355\u4f4d\uff1aMN/m^1.5\uff09");
    model.param().set("mP", "3.1", "Paris \u5b9a\u5f8b\u6307\u6570");
    model.param().set("ar", "pi*Xa/(2*Wp)", "\u4e2d\u95f4\u88c2\u7eb9\u957f\u5ea6\u53c2\u6570");
    model.param()
         .set("K1r", "Q0/1[Pa]*sqrt(pi*Xa/1[m])*sqrt(tan(ar)/ar)/cos(ar)*(0.752+2.02*(Xa/Wp)+0.37*(1-sin(ar))^3)", "\u53c2\u8003\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("dadN", "AP*(solid.crack1.jint1.KI/1e6)^mP");
    model.component("comp1").variable("var1")
         .descr("dadN", "\u88c2\u7eb9\u589e\u957f\u7387\uff08\u7c73/\u5468\u671f\uff09");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Wp", "Hp"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "Xa", 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94a2");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"206[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "Th");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(2, 4);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "20[MPa]", "0"});
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(4);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").create("crack1", "Crack", 1);
    model.component("comp1").physics("solid").feature("crack1").set("CrackSurface", "Symmetric");
    model.component("comp1").physics("solid").feature("crack1").selection().set(2);
    model.component("comp1").physics("solid").feature("crack1").selection("CrackFront").set(3);
    model.component("comp1").physics("solid").feature("crack1").create("jint1", "JIntegral", 0);
    model.component("comp1").physics("solid").feature("crack1").feature().duplicate("jint2", "jint1");
    model.component("comp1").physics("solid").feature("crack1").feature("jint2")
         .set("r", "solid.crack1.crackSize*0.7");
    model.component("comp1").physics("solid").feature("crack1").create("jint3", "JIntegral", 0);
    model.component("comp1").physics("solid").feature("crack1").feature("jint3").set("IntegrationPath", "Edges");
    model.component("comp1").physics("solid").feature("crack1").feature("jint3").selection("Path").set(1, 3, 5);
    model.component("comp1").physics("solid").feature("crack1").create("vce1", "VirtualCrackExtension", 1);
    model.component("comp1").physics("solid").feature("crack1").feature("vce1").set("parametricStudy", "yes");
    model.component("comp1").physics("solid").feature("crack1").feature("vce1")
         .setIndex("parameterName", "Xa", 0, 0);
    model.component("comp1").physics("solid").feature("crack1").feature("vce1")
         .setIndex("parameterRange", "range(0.5,da,0.7)", 0, 0);
    model.component("comp1").physics("solid").feature("crack1").feature("vce1").setIndex("parameterUnit", "m", 0, 0);
    model.component("comp1").physics("solid").feature("crack1").feature("vce1")
         .runCommand("createDeformedGeometryandStudy");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcrack1std").createAutoSequences("all");

    model.batch("solidcrack1p").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").setIndex("looplevel", 21, 1);
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
    model.result("pg1").feature("surf1").set("smooth", "none");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -1);
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").setIndex("looplevel", 21, 1);
    model.result("pg2").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg2").create("mesh1", "Mesh");
    model.result("pg2").feature("mesh1").set("meshdomain", "surface");
    model.result("pg2").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg2").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg2").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg2").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg2").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg2").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg2").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0, 1, 1);
    model.result().dataset("mir1").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").setIndex("looplevel", 11, 1);
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", 200);
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").label("\u8fb9\u754c\u8f7d\u8377");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"solid.fax", "solid.fay"});
    model.result("pg1").feature("arwl1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg1").feature("arwl1").set("titletype", "none");
    model.result("pg1").feature("arwl1").set("placement", "elements");
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", "1e-8");
    model.result("pg1").feature("arwl1").set("inheritplot", "surf1");
    model.result("pg1").feature("arwl1").set("inheritrange", false);
    model.result("pg1").feature("arwl1").set("inheritcolor", false);
    model.result("pg1").feature("arwl1").set("inheritarrowscale", false);
    model.result("pg1").feature("arwl1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("arwl1").feature("col1").set("expr", "solid.famag");
    model.result("pg1").feature("arwl1").feature("col1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b\uff0c\u5927\u5c0f");
    model.result("pg1").feature("arwl1").feature("col1").set("coloring", "gradient");
    model.result("pg1").feature("arwl1").feature("col1").set("topcolor", "red");
    model.result("pg1").feature("arwl1").feature("col1").set("bottomcolor", "gray");
    model.result("pg1").feature("arwl1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").feature("arwl1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").set("smooth", "none");
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 200);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scale", 50);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scale", 200);
    model.result("pg1").run();
    model.result("pg1").feature("line1").active(true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("coloring", "colortable");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "auto");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").setIndex("looplevel", 21, 1);
    model.result("pg3").label("\u88c2\u7eb9 (solid)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("frametype", "material");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("arpt1", "ArrowPoint");
    model.result("pg3").feature("arpt1")
         .set("expr", new String[]{"solid.crack1.e1X*solid.crack1.crackSize ", "solid.crack1.e1Y*solid.crack1.crackSize "});
    model.result("pg3").feature("arpt1").set("scaleactive", true);
    model.result("pg3").feature("arpt1").set("scale", "1");
    model.result("pg3").feature("arpt1").label("\u88c2\u7eb9\u6269\u5c55\u65b9\u5411 (\u88c2\u7eb9 1)");
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "sqrt((X-solid.crack1.jint1.Xp)^2+(Y-solid.crack1.jint1.Yp)^2)");
    model.result("pg3").feature("con1").set("levelmethod", "levels");
    model.result("pg3").feature("con1").set("levels", new String[]{"solid.crack1.jint1.r"});
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("color", "magenta");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").label("J \u79ef\u5206 1, \u79ef\u5206\u8def\u5f84");
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("text", "J=eval(solid.crack1.jint1.J)");
    model.result("pg3").feature("ann1").set("posxexpr", "solid.crack1.jint1.Xp");
    model.result("pg3").feature("ann1").set("posyexpr", "solid.crack1.jint1.Yp");
    model.result("pg3").feature("ann1").label("J \u79ef\u5206 1, \u8ba1\u7b97");
    model.result("pg3").create("con2", "Contour");
    model.result("pg3").feature("con2").set("expr", "sqrt((X-solid.crack1.jint2.Xp)^2+(Y-solid.crack1.jint2.Yp)^2)");
    model.result("pg3").feature("con2").set("levelmethod", "levels");
    model.result("pg3").feature("con2").set("levels", new String[]{"solid.crack1.jint2.r"});
    model.result("pg3").feature("con2").set("coloring", "uniform");
    model.result("pg3").feature("con2").set("color", "magenta");
    model.result("pg3").feature("con2").set("colorlegend", false);
    model.result("pg3").feature("con2").label("J \u79ef\u5206 2, \u79ef\u5206\u8def\u5f84");
    model.result("pg3").create("ann2", "Annotation");
    model.result("pg3").feature("ann2").set("text", "J=eval(solid.crack1.jint2.J)");
    model.result("pg3").feature("ann2").set("posxexpr", "solid.crack1.jint2.Xp");
    model.result("pg3").feature("ann2").set("posyexpr", "solid.crack1.jint2.Yp");
    model.result("pg3").feature("ann2").label("J \u79ef\u5206 2, \u8ba1\u7b97");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "sqrt(solid.crack1.jint3.mX^2+solid.crack1.jint3.mY^2)");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "magenta");
    model.result("pg3").feature("line1").label("J \u79ef\u5206 3, \u79ef\u5206\u8def\u5f84");
    model.result("pg3").create("ann3", "Annotation");
    model.result("pg3").feature("ann3").set("text", "J=eval(solid.crack1.jint3.J)");
    model.result("pg3").feature("ann3").set("posxexpr", "solid.crack1.jint3.Xp");
    model.result("pg3").feature("ann3").set("posyexpr", "solid.crack1.jint3.Yp");
    model.result("pg3").feature("ann3").label("J \u79ef\u5206 3, \u8ba1\u7b97");
    model.result("pg3").label("\u88c2\u7eb9 (solid)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").stepFirst(1);
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg_dset2solid", "EvaluationGroup");
    model.result().evaluationGroup("eg_dset2solid").label("\u65ad\u88c2\u529b\u5b66\u7ed3\u679c (solid)");
    model.result().evaluationGroup("eg_dset2solid").set("data", "dset2");
    model.result().evaluationGroup("eg_dset2solid").set("transpose", true);
    model.result().evaluationGroup("eg_dset2solid").create("jint", "EvalGlobal");
    model.result().evaluationGroup("eg_dset2solid").feature("jint").label("J \u79ef\u5206");
    model.result().evaluationGroup("eg_dset2solid").feature("jint").setIndex("expr", "solid.crack1.jint1.J", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("jint")
         .setIndex("descr", "J \u79ef\u5206 [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("jint").setIndex("expr", "solid.crack1.jint2.J", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("jint")
         .setIndex("descr", "J \u79ef\u5206 [crack1/jint2]", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("jint").setIndex("expr", "solid.crack1.jint3.J", 2);
    model.result().evaluationGroup("eg_dset2solid").feature("jint")
         .setIndex("descr", "J \u79ef\u5206 [crack1/jint3]", 2);
    model.result().evaluationGroup("eg_dset2solid").create("sif1", "EvalGlobal");
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .label("\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201c1\u201d");
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.jint1.KI", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.jint2.KI", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d [crack1/jint2]", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.jint3.KI", 2);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d [crack1/jint3]", 2);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.vce1.KIG", 3);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d [crack1/vce1]", 3);
    model.result().evaluationGroup("eg_dset2solid").create("sif2", "EvalGlobal");
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .label("\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201c2\u201d");
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.jint1.KII", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cII\u201d [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.jint2.KII", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cII\u201d [crack1/jint2]", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.jint3.KII", 2);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cII\u201d [crack1/jint3]", 2);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.vce1.KIIG", 3);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cII\u201d [crack1/vce1]", 3);
    model.result().evaluationGroup("eg_dset2solid").create("grel", "EvalGlobal");
    model.result().evaluationGroup("eg_dset2solid").feature("grel").label("\u80fd\u91cf\u91ca\u653e\u7387");
    model.result().evaluationGroup("eg_dset2solid").feature("grel").setIndex("expr", "solid.crack1.vce1.G", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("grel")
         .setIndex("descr", "\u80fd\u91cf\u91ca\u653e\u7387 [crack1/vce1]", 0);
    model.result().evaluationGroup("eg_dset2solid").label("\u65ad\u88c2\u529b\u5b66\u7ed3\u679c (solid)");
    model.result().evaluationGroup("eg_dset2solid").feature().remove("sif2");
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "K1r", 3);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u53c2\u8003\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50", 3);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("expr", "(solid.crack1.jint2.KI-K1r)/K1r*100", 4);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u4e0e\u53c2\u8003\u503c\u7684\u767e\u5206\u6bd4\u5dee\u5f02", 4);
    model.result().evaluationGroup("eg_dset2solid").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg_dset2solid").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("J \u79ef\u5206\u548c\u80fd\u91cf\u91ca\u653e\u7387");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "manualindices", 1);
    model.result("pg4").setIndex("looplevelindices", "range(2,20)", 1);
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"solid.crack1.jint1.J"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"J \u79ef\u5206"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"J/m^2"});
    model.result("pg4").feature("glob1").setIndex("descr", "J \u79ef\u5206\uff0c\u7b49\u503c\u7ebf 1", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "solid.crack1.vce1.G", 1);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "interp");
    model.result("pg4").feature("glob1").set("autosolution", false);
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u88c2\u7eb9\u957f\u5ea6 (m)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u80fd\u91cf\u91ca\u653e\u7387 (J/m^2)");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u88c2\u7eb9\u589e\u957f\u7387");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "dadN", 0);
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u88c2\u7eb9\u957f\u5ea6 (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u88c2\u7eb9\u589e\u957f\u7387\uff08\u7c73/\u5468\u671f\uff09");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u5faa\u73af\u6b21\u6570");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("expr", "1/dadN", 0);
    model.result().numerical("gev1").set("dataseries", "integral");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5faa\u73af\u6b21\u6570");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();

    model.title("\u5355\u8fb9\u88c2\u7eb9");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u542b\u5355\u8fb9\u88c2\u7eb9\u7684\u5e73\u677f\u53d7\u5230\u62c9\u4f38\u8f7d\u8377\u7684\u60c5\u51b5\u3002\u8be5\u8f7d\u8377\u5de5\u51b5\u7684\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50 KI \u7531\u4e09\u4e2a\u4e0d\u540c\u7b49\u503c\u7ebf\u7684 J \u79ef\u5206\u786e\u5b9a\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcrack1sol").clearSolutionData();
    model.sol("solidcrack1solp").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();

    model.label("single_edge_crack.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
