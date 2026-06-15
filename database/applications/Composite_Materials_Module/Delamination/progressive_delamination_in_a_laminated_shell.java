/*
 * progressive_delamination_in_a_laminated_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:32 by COMSOL 6.3.0.290. */
public class progressive_delamination_in_a_laminated_shell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Delamination");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lb", "100[mm]", "\u957f\u5ea6");
    model.param().set("wb", "50[mm]", "\u5bbd\u5ea6");
    model.param().set("hb", "1.5[mm]", "\u5c42\u539a\u5ea6");
    model.param().set("pn", "1e6[N/mm^3]", "\u7f5a\u521a\u5ea6");
    model.param().set("N_strength", "80[MPa]", "\u6cd5\u5411\u6297\u62c9\u5f3a\u5ea6");
    model.param().set("S_strength", "100[MPa]", "\u526a\u5207\u5f3a\u5ea6");
    model.param().set("GIc", "0.969[kJ/m^2]", "\u6a21\u5f0f I \u4e34\u754c\u80fd\u91cf\u91ca\u653e\u7387");
    model.param().set("GIIc", "1.719[kJ/m^2]", "\u6a21\u5f0f II \u4e34\u754c\u80fd\u91cf\u91ca\u653e\u7387");
    model.param().set("eta", "2.284", "Benzeggagh \u548c Kenane (B-K) \u51c6\u5219\u6307\u6570");
    model.param().set("Fmax", "28[kN]", "\u6700\u5927\u4f5c\u7528\u529b");
    model.param().set("para", "0", "\u8f7d\u8377\u53c2\u6570");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat1")
         .label("Unidirectional fiber lamina: AS4/APC2 carbon/PEEK thermoplastic [fiber volume fraction 50%]");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1570[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.2E-6[1/K]", "0", "0", "0", "24E-6[1/K]", "0", "0", "0", "24E-6[1/K]"});
    model.material("mat1").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"122.7[GPa]", "10.1[GPa]"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.25", "0.45"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", "5.5[GPa]");
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[0/45]");
    model.material("lmat1").setIndex("rotation", 0, 0);
    model.material("lmat1").setIndex("thickness", "hb", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 0, 1);
    model.material("lmat1").setIndex("thickness", "hb", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 0, 1);
    model.material("lmat1").setIndex("thickness", "hb", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("rotation", 45, 1);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("F", "Fmax*sin(pi*para)");
    model.component("comp1").variable("var1").descr("F", "\u4f5c\u7528\u529b");

    model.component("comp1").coordSystem("sys1").set("frametype", "material");
    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"lb/2", "wb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "lb/10");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"lb/5", "wb/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"lb/5", "wb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input")
         .set("c1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1")
         .set("pos", new String[]{"lb/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 8, 20);
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");

    model.component("comp1").physics("lshell").create("del1", "Delamination", 2);
    model.component("comp1").physics("lshell").feature("del1").selection().set(2, 5);
    model.component("comp1").physics("lshell").feature("del1").set("InitialState", "Delaminated");
    model.component("comp1").physics("lshell").feature("del1").set("pn", "pn");
    model.component("comp1").physics("lshell").create("del2", "Delamination", 2);
    model.component("comp1").physics("lshell").feature("del2").selection().set(1, 3, 4, 6);
    model.component("comp1").physics("lshell").feature("del2").set("StiffnessInput", "UserDefined");
    model.component("comp1").physics("lshell").feature("del2").set("kPerArea", new String[]{"pn", "pn", "pn"});
    model.component("comp1").physics("lshell").feature("del2").set("sigmat", "N_strength");
    model.component("comp1").physics("lshell").feature("del2").set("sigmas", "S_strength");
    model.component("comp1").physics("lshell").feature("del2").set("Gct", "GIc");
    model.component("comp1").physics("lshell").feature("del2").set("Gcs", "GIIc");
    model.component("comp1").physics("lshell").feature("del2").set("FailureCriterion", "BK");
    model.component("comp1").physics("lshell").feature("del2").set("alpha", "eta");
    model.component("comp1").physics("lshell").feature("del2").set("PenaltyFactor", "FromAdhesiveStiffness");
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().set(1, 23);
    model.component("comp1").physics("lshell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("lshell").feature("fl1").selection().all();
    model.component("comp1").physics("lshell").feature("fl1").set("applyTo", "top");
    model.component("comp1").physics("lshell").feature("fl1").set("forceType", "TotalForce");
    model.component("comp1").physics("lshell").feature("fl1").set("force", new String[]{"0", "0", "-F"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(4, 5, 6, 8, 9, 10, 15, 16, 17, 19, 20, 21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 25);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "lb", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "lb", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.025,0.5) range(0.55,0.05,1)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1lshelllshl", "LayeredMaterial");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1lshelllshl");
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").label("\u5e94\u529b (lshell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", "2e3");

    model.component("comp1").view("view1").set("showgrid", true);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("lss1", "LayeredMaterialSlice");
    model.result("pg2").feature("lss1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg2").feature("lss1").set("threshold", "manual");
    model.result("pg2").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("lss1").set("colortable", "Prism");
    model.result("pg2").feature("lss1").set("colortabletrans", "none");
    model.result("pg2").feature("lss1").set("colorscalemode", "linear");
    model.result("pg2").feature("lss1").set("locdef", "relative");
    model.result("pg2").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg2").feature("lss1").create("def", "Deform");
    model.result("pg2").feature("lss1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("lss1").feature("def").set("scale", "1");
    model.result("pg2").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("lss1").set("locdef", "layermidplanes");
    model.result("pg2").feature("lss1").set("slicedisplacement", "linear");
    model.result("pg2").feature("lss1").set("orientationlinear", "y");
    model.result("pg2").feature("lss1").set("rangecoloractive", true);
    model.result("pg2").feature("lss1").set("rangecolormax", "1e3");
    model.result("pg2").run();
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u754c\u9762\u5b8c\u597d\u60c5\u51b5\uff0c100% \u635f\u4f24");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("lss1", "LayeredMaterialSlice");
    model.result("pg3").feature("lss1").set("expr", "lshell.idmg");
    model.result("pg3").feature("lss1").set("locdef", "interfaces");
    model.result("pg3").feature("lss1").set("colortable", "Traffic");
    model.result("pg3").feature("lss1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("lss1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("lss1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u9ecf\u9644\u5e94\u529b\uff0ct1 \u65b9\u5411");
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("lss1", "LayeredMaterialSlice");
    model.result("pg4").feature("lss1").set("expr", "lshell.fst1");
    model.result("pg4").feature("lss1").set("descr", "\u9ecf\u9644\u5e94\u529b\uff0ct1 \u5206\u91cf");
    model.result("pg4").feature("lss1").set("locdef", "interfaces");
    model.result("pg4").feature("lss1").set("colortable", "RainbowLight");
    model.result("pg4").feature("lss1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("lss1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("lss1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("lss1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").label("\u591a\u5c42\u6750\u6599\uff08\u754c\u9762\uff09");
    model.result().dataset("lshl1").set("evaluatein", "interfaces");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u635f\u4f24\u9762\u79ef");
    model.result().evaluationGroup("eg1").set("data", "lshl1");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().all();
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "gpeval(4,lshell.idmg)/(lb*wb)", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("unit", "%", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "\u635f\u4f24\u9762\u79ef", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f7d\u8377 vs. \u635f\u4f24");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "para (1)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u603b\u635f\u4f24\u9762\u79ef (%)");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").set("legendpos", "lowermiddle");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("data", "dset1");
    model.result("pg5").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg5").feature("glob1").setIndex("expr", "F", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg5").run();
    model.result("pg5").run();
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
    model.result().export("anim1").label("\u52a8\u753b\uff1a\u5e94\u529b");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("frametime", 0.3);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u52a8\u753b\uff1a\u754c\u9762\u5b8c\u597d\u60c5\u51b5");
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").label("\u52a8\u753b\uff1a\u9ecf\u9644\u5e94\u529b");
    model.result().export("anim3").set("plotgroup", "pg4");
    model.result("pg3").run();

    model.title("\u5c42\u538b\u58f3\u4e2d\u7684\u6e10\u8fdb\u8131\u5c42");

    model
         .description("\u590d\u5408\u6750\u6599\u4e2d\u7684\u754c\u9762\u7834\u574f\u6216\u8131\u5c42\u662f\u4e00\u79cd\u5e38\u89c1\u73b0\u8c61\uff0c\u53ef\u4ee5\u4f7f\u7528\u201c\u5185\u805a\u529b\u6a21\u578b\u201d(CZM) \u8fdb\u884c\u6a21\u62df\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5728\u591a\u5c42\u58f3\u4e2d\u901a\u8fc7\u53cc\u7ebf\u6027\u7275\u5f15-\u5206\u79bb\u5b9a\u5f8b\u5b9e\u73b0 CZM\uff0c\u7528\u4e8e\u9884\u6d4b\u6df7\u5408\u6a21\u5f0f\u8f6f\u5316\u5f00\u59cb\u548c\u8131\u5c42\u4f20\u64ad\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("progressive_delamination_in_a_laminated_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
