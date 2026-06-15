/*
 * micromechanical_model_of_a_particulate_composite.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:12 by COMSOL 6.3.0.290. */
public class micromechanical_model_of_a_particulate_composite {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Material_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.param().label("\u51e0\u4f55\u5c5e\u6027");
    model.param().set("para", "0");
    model.param().descr("para", "\u53c2\u6570");
    model.param().set("L", "1[m]");
    model.param().descr("L", "\u57fa\u672c\u5355\u5143\u957f\u5ea6");
    model.param().set("dp", "0.4[m]");
    model.param().descr("dp", "\u7c92\u5f84");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("E_p", "230[GPa]", "\u9897\u7c92\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("nu_p", "0.2", "\u9897\u7c92\u7684\u6cca\u677e\u6bd4");
    model.param("par2").set("E_m", "10[GPa]", "\u57fa\u4f53\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("nu_m", "0.35", "\u57fa\u4f53\u7684\u6cca\u677e\u6bd4");
    model.param("par2")
         .set("g1", "0.01", "\u57fa\u4f53\u7684\u504f Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 1");
    model.param("par2")
         .set("g2", "0.05", "\u57fa\u4f53\u7684\u504f Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 2");
    model.param("par2")
         .set("g3", "0.08", "\u57fa\u4f53\u7684\u504f Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 3");
    model.param("par2").set("Tau1", "0.01[s]", "\u57fa\u4f53\u7684\u5f1b\u8c6b\u65f6\u95f4\uff0c\u5206\u652f 1");
    model.param("par2").set("Tau2", "0.1[s]", "\u57fa\u4f53\u7684\u5f1b\u8c6b\u65f6\u95f4\uff0c\u5206\u652f 2");
    model.param("par2").set("Tau3", "1[s]", "\u57fa\u4f53\u7684\u5f1b\u8c6b\u65f6\u95f4\uff0c\u5206\u652f 3");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Particulate_Composites\\particulate_primitive_cubic.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u5f02\u6784 RUC");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dp", "dp");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wm", "L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dm", "L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hm", "L");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u5747\u8d28 RUC");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "L", "L"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"2*L", "0", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u57fa\u4f53");
    model.component("comp1").material("mat1").selection().named("geom1_pi1_dif1_dom");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E_m"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu_m"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u9897\u7c92");
    model.component("comp1").material("mat2").selection().named("geom1_pi1_sph1_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"E_p"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"nu_p"});

    model.component("comp1").physics("solid").label("\u56fa\u4f53\u529b\u5b66\uff1a\u5f02\u6784 RUC");
    model.component("comp1").physics("solid").selection().named("geom1_pi1_boxsel1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1")
         .label("\u5f39\u6027\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027");
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().set();
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().named("geom1_pi1_unisel1");
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp2", "bp1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().set();
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().named("geom1_pi1_unisel2");
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp3", "bp2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().set();
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().named("geom1_pi1_unisel3");
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(6, 7, 8, 9, 10, 11, 12, 13);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", 0.07);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmin", 0.05);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("geom1_pi1_sel1");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("geom1_pi1_sel2");
    model.component("comp1").mesh("mesh1").create("id2", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id2").selection("group1").named("geom1_pi1_sel3");
    model.component("comp1").mesh("mesh1").feature("id2").selection("group2").named("geom1_pi1_sel4");
    model.component("comp1").mesh("mesh1").create("id3", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id3").selection("group1").named("geom1_pi1_sel5");
    model.component("comp1").mesh("mesh1").feature("id3").selection("group2").named("geom1_pi1_sel6");
    model.component("comp1").mesh("mesh1").run("id3");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_pi1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurve", 0.4);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(15);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(28, 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcp1std")
         .label("\u5f39\u6027\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027\u7814\u7a76\uff08\u5f02\u6784 RUC\uff09");
    model.study("solidcp1std").createAutoSequences("all");

    model.sol("solidcp1sol").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 6, 0);
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
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("solidcp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset1");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u5f39\u6027\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027\u7814\u7a76\uff08\u5f02\u6784 RUC\uff09)");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevescp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("expr", "root.comp1.solid.cp1.D");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("descr", "\u5f39\u6027\u77e9\u9635");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "GPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u5f39\u6027\u54cd\u5e94");
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().named("geom1_pi1_boxsel1");
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "von Mises \u5e94\u529b (GPa)");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").feature("vol1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().named("geom1_pi1_dif1_dom");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature().remove("def");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").feature("vol2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").set("inheritplot", "vol1");
    model.result("pg1").feature("vol2").set("applytodatasetedgesplotarr", false);
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("sel1").selection().set();
    model.result("pg1").feature("vol2").feature("sel1").selection().named("geom1_pi1_sph1_dom");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("paramindicator", "Load case 1");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").set("paramindicator", "Load case 4");
    model.result("pg1").run();

    model.component("comp1").physics("solid").feature("cp1").runCommand("createMaterialbyValue");

    model.func().create("step1", "Step");
    model.func("step1").set("funcname", "strainFunction");
    model.func("step1").set("location", "5e-4[s]");
    model.func("step1").set("smooth", "1e-3[s]");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u5f02\u6784 RUC");
    model.component("comp1").variable("var1").set("G_m", "E_m/(2*(1+nu_m))");
    model.component("comp1").variable("var1").descr("G_m", "\u57fa\u4f53\u7684\u526a\u5207\u6a21\u91cf");
    model.component("comp1").variable("var1").set("sum_g", "g1+g2+g3");
    model.component("comp1").variable("var1").descr("sum_g", "\u6743\u91cd\u4e4b\u548c");
    model.component("comp1").variable("var1").set("G_m0", "G_m/(1-sum_g)");
    model.component("comp1").variable("var1")
         .descr("G_m0", "\u57fa\u4f53\u7684\u77ac\u65f6\u526a\u5207\u6a21\u91cf");

    model.component("comp1").physics("solid").feature("lemm1").create("vis1", "Viscoelasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").selection()
         .named("geom1_pi1_dif1_dom");
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", "G_m0*g1", 0, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", "Tau1", 0, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", "G_m0*g2", 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", "Tau2", 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", "G_m0*g3", 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", "Tau3", 2, 0);
    model.component("comp1").physics("solid").feature().duplicate("cp2", "cp1");
    model.component("comp1").physics("solid").feature("cp2")
         .label("\u9ecf\u5f39\u6027\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027");
    model.component("comp1").physics("solid").feature("cp2").set("computeElasticityMatrixStandard", false);
    model.component("comp1").physics("solid").feature("cp2")
         .set("eavgi", new String[]{"(para==1)*strainFunction(t)", "(para==2)*0.5*strainFunction(t)", "0", "(para==2)*0.5*strainFunction(t)", "0", "0", "0", "0", "0"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1")
         .label("\u9ecf\u5f39\u6027\u54cd\u5e94\u7684\u77ac\u6001\u7814\u7a76\uff08\u5f02\u6784 RUC\uff09");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "dp", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "dp", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "para", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.5e-4,9.5e-4) 10^{range(-3,0.1,1.5)}");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"solid/cp1"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "5e-7");
    model.sol("sol1").feature("t1").set("storeudot", false);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 66, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").set("applyselectiontodatasetedges", false);
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b\uff0c\u9ecf\u5f39\u6027\u54cd\u5e94");
    model.result("pg2").selection().geom("geom1", 3);
    model.result("pg2").selection().named("geom1_pi1_boxsel1");
    model.result("pg2").set("applyselectiontodatasetedges", true);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "von Mises \u5e94\u529b (GPa)");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").feature("vol1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("geom1_pi1_dif1_dom");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature().remove("def");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("vol2", "vol1");
    model.result("pg2").feature("vol2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("vol2").set("inheritplot", "vol1");
    model.result("pg2").feature("vol2").set("applytodatasetedgesplotarr", false);
    model.result("pg2").run();
    model.result("pg2").feature("vol2").feature("sel1").selection().set();
    model.result("pg2").feature("vol2").feature("sel1").selection().named("geom1_pi1_sph1_dom");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").set("paramindicator", "para(2)=1 Time=31.623 s");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("paramindicator", "para(2)=2 Time=31.623 s");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e73\u5747\u6b63\u5e94\u529b\u548c\u526a\u5207\u5e94\u529b");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u5e73\u5747\u5e94\u529b (GPa)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u5168\u5c40\uff1a\u5e73\u5747\u5e94\u529b (GPa)");
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("data", "dset3");
    model.result("pg3").feature("glob1").setIndex("looplevelinput", "first", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "solid.cp2.savgXX", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u5e73\u5747\u5e94\u529b\uff0cXX \u65b9\u5411", 0);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u5e73\u5747\u6b63\u5e94\u529b", 0);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").setIndex("looplevelinput", "last", 1);
    model.result("pg3").feature("glob2").setIndex("expr", "solid.cp2.savgXY", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "\u5e73\u5747\u5e94\u529b\uff0cXY \u65b9\u5411", 0);
    model.result("pg3").feature("glob2").setIndex("legends", "\u5e73\u5747\u526a\u5207\u5e94\u529b", 0);
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4\uff1a\u6b63\u5e94\u529b\u54cd\u5e94");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "first", 1);
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "manualindices", 0);
    model.result().evaluationGroup("eg1").setIndex("looplevelindices", "range(21,1,61)", 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "t", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u65f6\u95f4", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "solid.cp2.savgXX", 1);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u8ba1\u7b97\u7ec4\uff1a\u526a\u5207\u5e94\u529b\u54cd\u5e94");
    model.result().evaluationGroup("eg2").set("data", "dset3");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "manualindices", 0);
    model.result().evaluationGroup("eg2").setIndex("looplevelindices", "range(21,1,61)", 0);
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "t", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "\u65f6\u95f4", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "solid.cp2.savgXY", 1);
    model.result().evaluationGroup("eg2").set("includeparameters", false);
    model.result().evaluationGroup("eg2").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("evaluationgroup", "eg1");
    model.nodeGroup("grp1").add("evaluationgroup", "eg2");
    model.nodeGroup("grp1").label("\u5f02\u6784 RUC");

    model.param().create("par3");
    model.param("par3").label("\u4f18\u5316\u53c2\u6570");
    model.param("par3").set("gg1", "0");
    model.param("par3")
         .descr("gg1", "\u5747\u8d28\u6750\u6599\u7684\u504f\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 1");
    model.param("par3").set("gg2", "0");
    model.param("par3")
         .descr("gg2", "\u5747\u8d28\u6750\u6599\u7684\u504f\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 2");
    model.param("par3").set("gg3", "0");
    model.param("par3")
         .descr("gg3", "\u5747\u8d28\u6750\u6599\u7684\u504f\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 3");
    model.param("par3").set("kg1", "0");
    model.param("par3")
         .descr("kg1", "\u5747\u8d28\u6750\u6599\u7684\u4f53\u79ef\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 1");
    model.param("par3").set("kg2", "0");
    model.param("par3")
         .descr("kg2", "\u5747\u8d28\u6750\u6599\u7684\u4f53\u79ef\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 2");
    model.param("par3").set("kg3", "0");
    model.param("par3")
         .descr("kg3", "\u5747\u8d28\u6750\u6599\u7684\u4f53\u79ef\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\uff0c\u5206\u652f 3");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf\uff1a\u5747\u8d28\u6750\u6599");
    model.component("comp1").variable("var2").set("G_H", "solid2.D66");
    model.component("comp1").variable("var2").descr("G_H", "\u5747\u8d28\u6750\u6599\u7684\u526a\u5207\u6a21\u91cf");
    model.component("comp1").variable("var2").set("K_H", "solid2.D11-4*G_H/3");
    model.component("comp1").variable("var2").descr("K_H", "\u5747\u8d28\u6750\u6599\u7684\u4f53\u79ef\u6a21\u91cf");
    model.component("comp1").variable("var2").set("sum_gH", "gg1+gg2+gg3");
    model.component("comp1").variable("var2").descr("sum_gH", "\u6743\u91cd\u4e4b\u548c");
    model.component("comp1").variable("var2").set("sum_kH", "kg1+kg2+kg3");
    model.component("comp1").variable("var2").descr("sum_kH", "\u6743\u91cd\u4e4b\u548c");
    model.component("comp1").variable("var2").set("G_H0", "G_H/(1-sum_gH)");
    model.component("comp1").variable("var2")
         .descr("G_H0", "\u5747\u8d28\u6750\u6599\u7684\u77ac\u65f6\u526a\u5207\u6a21\u91cf");
    model.component("comp1").variable("var2").set("K_H0", "K_H/(1-sum_kH)");
    model.component("comp1").variable("var2")
         .descr("K_H0", "\u5747\u8d28\u6750\u6599\u7684\u77ac\u65f6\u4f53\u79ef\u6a21\u91cf");

    model.component("comp1").material().create("matlnk1", "Link");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("matlnk1").label("\u5747\u8d28\u6750\u6599");
    model.component("comp1").material("matlnk1").selection().set(3);

    model.component("comp1").physics().create("solid2", "SolidMechanics", "geom1");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/solid2", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid2", true);

    model.component("comp1").physics("solid2").label("\u56fa\u4f53\u529b\u5b66\uff1a\u5747\u8d28 RUC");
    model.component("comp1").physics("solid2").selection().set(3);
    model.component("comp1").physics("solid2").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid2").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid2").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("solid2").feature("lemm1").create("vis1", "Viscoelasticity", 3);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("Gvm", "G_H0*gg1", 0, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", "Tau1", 0, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("Gvm", "G_H0*gg2", 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", "Tau2", 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("Gvm", "G_H0*gg3", 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis1").setIndex("tauvm", "Tau3", 2, 0);
    model.component("comp1").physics("solid2").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid2").feature("cp1")
         .label("\u5355\u5143\u5468\u671f\u6027\uff1a\u526a\u5207\u5e94\u53d8\u8f7d\u8377");
    model.component("comp1").physics("solid2").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid2").feature("cp1")
         .set("eavgi", new String[]{"0", "0.5*strainFunction(t)", "0", "0.5*strainFunction(t)", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("solid2").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid2").feature("cp1").feature("bp1").selection().set(15, 20);
    model.component("comp1").physics("solid2").feature("cp1").feature().duplicate("bp2", "bp1");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp2").selection().set(16, 19);
    model.component("comp1").physics("solid2").feature("cp1").feature().duplicate("bp3", "bp2");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp3").selection().set(17, 18);

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").label("\u526a\u5207\u5e94\u529b\u54cd\u5e94");
    model.component("comp1").common("lso1").set("source", "resultTable");
    model.component("comp1").common("lso1").set("resultTable", "evalGroup:eg2");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col2", "comp1.solid2.cp1.savgXY");
    model.component("comp1").common("lso1").setEntry("variableName", "col2", "shear_stress");
    model.component("comp1").common("lso1").setEntry("unit", "col2", "GPa");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid2", true);
    model.study("std2")
         .label("\u504f\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\u4f30\u8ba1\uff08\u5747\u8d28 RUC\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").set("source", "none");
    model.study("std2").feature("lsqo").setIndex("pname", "dp", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "0.4[m]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "dp", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "0.4[m]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "E_m", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "10[GPa]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "E_m", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "10[GPa]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "E_p", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "230[GPa]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "E_p", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "230[GPa]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "gg1", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "g1", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 0.1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 0);
    model.study("std2").feature("lsqo").setIndex("ubound", 1, 0);
    model.study("std2").feature("lsqo").setIndex("pname", "gg2", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "g2", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 0.1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 1);
    model.study("std2").feature("lsqo").setIndex("ubound", 1, 1);
    model.study("std2").feature("lsqo").setIndex("pname", "gg3", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "g3", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 0.1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 2);
    model.study("std2").feature("lsqo").setIndex("ubound", 1, 2);
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid", false);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"solid"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("o1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol5").feature("o1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol5").feature("o1").feature("t1").set("initialstepbdf", "5e-5");
    model.sol("sol5").feature("o1").feature("t1").set("storeudot", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e73\u5747\u526a\u5207\u5e94\u529b");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5e73\u5747\u526a\u5207\u5e94\u529b (GPa)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u5168\u5c40\uff1a\u5e73\u5747\u526a\u5207\u5e94\u529b (GPa)");
    model.result("pg4").set("xlog", true);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "solid2.cp1.savgXY", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u5e73\u5747\u5e94\u529b\uff0cXY \u65b9\u5411", 0);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u5747\u8d28\u6750\u6599\u6a21\u578b", 0);
    model.result("pg4").run();
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg4").feature("tblp1").set("evaluationgroup", "eg2");
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linemarker", "point");
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("legendmethod", "manual");
    model.result("pg4").feature("tblp1").setIndex("legends", "\u5f02\u6784 RUC", 0);
    model.result("pg4").run();

    model.component("comp1").physics("solid2").feature("lemm1").feature().duplicate("vis2", "vis1");
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").set("deformationModel", "full");
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Kvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Gvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Gvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Kvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Gvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", 0, 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Kvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Gvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Gvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Kvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Gvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", 0, 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Kvm_f", "K_H0*kg1", 0, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2")
         .setIndex("Gvm_f", "G_H0*withsol('sol5',gg1)", 0, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", "Tau1", 0, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Kvm_f", "K_H0*kg2", 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2")
         .setIndex("Gvm_f", "G_H0*withsol('sol5',gg2)", 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", "Tau2", 1, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("Kvm_f", "K_H0*kg3", 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2")
         .setIndex("Gvm_f", "G_H0*withsol('sol5',gg3)", 2, 0);
    model.component("comp1").physics("solid2").feature("lemm1").feature("vis2").setIndex("tauvm_f", "Tau3", 2, 0);
    model.component("comp1").physics("solid2").feature().duplicate("cp2", "cp1");
    model.component("comp1").physics("solid2").feature("cp2")
         .label("\u5355\u5143\u5468\u671f\u6027\uff1a\u6b63\u5e94\u53d8\u8f7d\u8377");
    model.component("comp1").physics("solid2").feature("cp2")
         .set("eavgi", new String[]{"strainFunction(t)", "0", "0", "0", "0", "0", "0", "0", "0"});

    model.component("comp1").common().create("lso2", "LeastSquaresObjective");
    model.component("comp1").common("lso2").label("\u6b63\u5e94\u529b\u54cd\u5e94");
    model.component("comp1").common("lso2").set("source", "resultTable");
    model.component("comp1").common("lso2").set("resultTable", "evalGroup:eg1");
    model.component("comp1").common("lso2").setEntry("modelExpression", "col2", "comp1.solid2.cp2.savgXX");
    model.component("comp1").common("lso2").setEntry("variableName", "col2", "normal_stress");
    model.component("comp1").common("lso2").setEntry("unit", "col2", "GPa");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/solid", true);
    model.study("std3").feature("time").setSolveFor("/physics/solid2", true);
    model.study("std3")
         .label("\u4f53\u79ef\u5e94\u53d8 Prony \u7ea7\u6570\u53c2\u6570\u4f30\u8ba1\uff08\u5747\u8d28 RUC\uff09");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("lsqo", "LSQOptimization");
    model.study("std3").feature("lsqo").set("source", "none");
    model.study("std3").feature("lsqo").setIndex("objectiveActive", false, 0);
    model.study("std3").feature("lsqo").setIndex("pname", "dp", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "0.4[m]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "dp", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "0.4[m]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "E_m", 1);
    model.study("std3").feature("lsqo").setIndex("initval", "10[GPa]", 1);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std3").feature("lsqo").setIndex("pname", "E_m", 1);
    model.study("std3").feature("lsqo").setIndex("initval", "10[GPa]", 1);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std3").feature("lsqo").setIndex("pname", "E_p", 2);
    model.study("std3").feature("lsqo").setIndex("initval", "230[GPa]", 2);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "E_p", 2);
    model.study("std3").feature("lsqo").setIndex("initval", "230[GPa]", 2);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "kg1", 0);
    model.study("std3").feature("lsqo").setIndex("initval", 0.001, 0);
    model.study("std3").feature("lsqo").setIndex("scale", 0.001, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", 0, 0);
    model.study("std3").feature("lsqo").setIndex("ubound", 1, 0);
    model.study("std3").feature("lsqo").setIndex("pname", "kg2", 1);
    model.study("std3").feature("lsqo").setIndex("initval", 0.001, 1);
    model.study("std3").feature("lsqo").setIndex("scale", 0.001, 1);
    model.study("std3").feature("lsqo").setIndex("lbound", 0, 1);
    model.study("std3").feature("lsqo").setIndex("ubound", 1, 1);
    model.study("std3").feature("lsqo").setIndex("pname", "kg3", 2);
    model.study("std3").feature("lsqo").setIndex("initval", 0.001, 2);
    model.study("std3").feature("lsqo").setIndex("scale", 0.001, 2);
    model.study("std3").feature("lsqo").setIndex("lbound", 0, 2);
    model.study("std3").feature("lsqo").setIndex("ubound", 1, 2);
    model.study("std3").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").setSolveFor("/physics/solid", false);
    model.study("std3").feature("time")
         .set("disabledphysics", new String[]{"solid", "solid2/lemm1/vis1", "solid2/cp1"});
    model.study("std3").showAutoSequences("all");

    model.sol("sol6").feature("o1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol6").feature("o1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol6").feature("o1").feature("t1").set("initialstepbdf", "5e-5");
    model.sol("sol6").feature("o1").feature("t1").set("storeudot", false);

    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.study("std3").feature("lsqo").set("probewindow", "");

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5e73\u5747\u6b63\u5e94\u529b");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u5e73\u5747\u526a\u5207\u5e94\u529b (GPa)");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u5168\u5c40\uff1a\u5e73\u5747\u6b63\u5e94\u529b (GPa)");
    model.result("pg5").set("xlog", true);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "solid2.cp2.savgXX", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5e73\u5747\u5e94\u529b\uff0cXX \u65b9\u5411", 0);
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "\u5747\u8d28\u6750\u6599\u6a21\u578b", 0);
    model.result("pg5").run();
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp1").set("linestyle", "none");
    model.result("pg5").feature("tblp1").set("linemarker", "point");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("legendmethod", "manual");
    model.result("pg5").feature("tblp1").setIndex("legends", "\u5f02\u6784 RUC", 0);
    model.result("pg5").run();
    model.result().evaluationGroup().create("std2lsqoparam1", "EvaluationGroup");
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1").set("data", "dset4");
    model.result().evaluationGroup("std2lsqoparam1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std2lsqoparam1").set("transpose", true);
    model.result().evaluationGroup("std2lsqoparam1").set("includeparameters", false);
    model.result().evaluationGroup("std2lsqoparam1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "gg1", 0);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "gg2", 1);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "gg3", 2);
    model.result().evaluationGroup("std2lsqoparam1").run();
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1").label("\u5747\u8d28 Prony \u7ea7\u6570\u53c2\u6570");
    model.result().evaluationGroup("std2lsqoparam1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").set("data", "dset5");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("expr", "kg1", 0);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("expr", "kg2", 1);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("expr", "kg3", 2);
    model.result().evaluationGroup("std2lsqoparam1").run();
    model.result("pg4").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("evaluationgroup", "std2lsqoparam1");
    model.nodeGroup("grp2").label("\u5747\u8d28 RUC");

    model.study("solidcp1std").feature("solidcp1stat").set("useadvanceddisable", true);
    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/solid2", false);
    model.study("solidcp1std").feature("solidcp1stat")
         .set("disabledphysics", new String[]{"solid2", "solid/lemm1/vis1", "solid/cp2"});
    model.study("std1").feature("time").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"solid/cp1", "solid2"});
    model.study("std2").feature("time")
         .set("disabledphysics", new String[]{"solid", "solid2/lemm1/vis2", "solid2/cp2"});

    model.result("pg5").run();

    model.title("\u9897\u7c92\u590d\u5408\u6750\u6599\u7684\u7ec6\u89c2\u529b\u5b66\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u5206\u6790\u9897\u7c92\u590d\u5408\u6750\u6599\u5355\u80de\u7684\u7b80\u5316\u7ec6\u89c2\u529b\u5b66\u6a21\u578b\u3002\u590d\u5408\u6750\u6599\u7684\u5747\u5300\u5f39\u6027\u548c\u9ecf\u5f39\u6027\u5c5e\u6027\u6839\u636e\u9897\u7c92\u548c\u57fa\u4f53\u7684\u5355\u72ec\u5c5e\u6027\u8fdb\u884c\u8ba1\u7b97\uff0c\u5bf9\u590d\u5408\u6750\u6599\u5fae\u89c2\u7ed3\u6784\u8fdb\u884c\u77ac\u6001\u5206\u6790\u53ef\u4ee5\u7ed9\u51fa\u9ecf\u5f39\u6027\u54cd\u5e94\uff0c\u8be5\u54cd\u5e94\u7528\u4e8e\u4f7f\u7528\u66f2\u7ebf\u62df\u5408\u4f18\u5316\u6765\u786e\u5b9a\u5747\u5300\u5316\u9ecf\u5f39\u6027\u53c2\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("micromechanical_model_of_a_particulate_composite.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
