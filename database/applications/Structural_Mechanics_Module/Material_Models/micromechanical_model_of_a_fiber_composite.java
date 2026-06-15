/*
 * micromechanical_model_of_a_fiber_composite.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:12 by COMSOL 6.3.0.290. */
public class micromechanical_model_of_a_fiber_composite {

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("para", "0", "\u65e0\u91cf\u7eb2\u53c2\u6570");
    model.param().set("l", "0.1[m]", "\u57fa\u672c\u5355\u5143\u957f\u5ea6");
    model.param().set("V", "l^3", "\u57fa\u672c\u5355\u5143\u4f53\u79ef");
    model.param().set("v_f", "0.1", "\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.param().set("v_m", "1-v_f", "\u6811\u8102\u4f53\u79ef\u5206\u6570");
    model.param().set("V_f", "v_f*V", "\u7ea4\u7ef4\u4f53\u79ef");
    model.param().set("r_f", "sqrt(V_f/(pi*l))", "\u7ea4\u7ef4\u534a\u5f84");
    model.param().set("E1_f", "230[GPa]", "\u7ea4\u7ef4\u6768\u6c0f\u6a21\u91cf\uff0c11 \u65b9\u5411");
    model.param().set("E2_f", "15[GPa]", "\u7ea4\u7ef4\u6768\u6c0f\u6a21\u91cf\uff0c22 \u65b9\u5411");
    model.param().set("G12_f", "15[GPa]", "\u7ea4\u7ef4\u526a\u5207\u6a21\u91cf\uff0c12 \u65b9\u5411");
    model.param().set("G23_f", "7[GPa]", "\u7ea4\u7ef4\u526a\u5207\u6a21\u91cf\uff0c23 \u65b9\u5411");
    model.param().set("nu12_f", "0.2*para", "\u7ea4\u7ef4\u6cca\u677e\u6bd4\uff0c12 \u65b9\u5411");
    model.param().set("nu23_f", "0.07*para", "\u7ea4\u7ef4\u6cca\u677e\u6bd4\uff0c23 \u65b9\u5411");
    model.param().set("E_m", "4[GPa]", "\u6811\u8102\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu_m", "0.35*para", "\u6811\u8102\u6cca\u677e\u6bd4");
    model.param().set("G_m", "E_m/(2*(1+nu_m))", "\u6811\u8102\u526a\u5207\u6a21\u91cf");
    model.param().set("rho_f", "1800[kg/m^3]", "\u7ea4\u7ef4\u5bc6\u5ea6");
    model.param().set("rho_m", "1100[kg/m^3]", "\u6811\u8102\u5bc6\u5ea6");
    model.param().set("alpha1_f", "-0.6e-6[1/K]", "\u7ea4\u7ef4\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c\u7eb5\u5411");
    model.param().set("alpha2_f", "8.5e-6[1/K]", "\u7ea4\u7ef4\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c\u6a2a\u5411");
    model.param().set("alpha_m", "55e-6[1/K]", "\u6811\u8102\u70ed\u81a8\u80c0\u7cfb\u6570");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Fiber_Composites\\unidirectional_fiber_square_packing.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "df", "2*r_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wm", "l");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dm", "l");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hm", "l");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_temperature", "21[degC]");
    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1")
         .label("\u5f39\u6027\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027");
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().set(1, 5, 11, 12);
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp2", "bp1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().set(2, 10);
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp3", "bp2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().set(3, 4);
    model.component("comp1").physics("solid").feature("cp1").set("parametricStudy", "yes");
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterName", "v_f", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "range(0.1,0.1,0.7)", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");
    model.component("comp1").physics("solid").feature().duplicate("cp2", "cp1");
    model.component("comp1").physics("solid").feature("cp2")
         .label("\u70ed\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027");
    model.component("comp1").physics("solid").feature("cp2").set("BoundaryExpansion", "FreeExpansion");
    model.component("comp1").physics("solid").feature("cp2").set("computeThermalExpansion", true);

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u6750\u6599\u94fe\u63a5 1\uff1a\u73af\u6c27\u6811\u8102");
    model.component("comp1").material("matlnk1").selection().named("geom1_pi1_dif1_dom");
    model.material().create("mat1", "Common", "");
    model.component("comp1").material("matlnk1").set("link", "mat1");
    model.material("mat1").label("\u6750\u6599 1\uff1a\u73af\u6c27\u6811\u8102");
    model.material("mat1").propertyGroup().create("Orthotropic", "Orthotropic", "Orthotropic");
    model.material("mat1").propertyGroup("Orthotropic").set("Evector", new String[]{"E_m", "E_m", "E_m"});
    model.material("mat1").propertyGroup("Orthotropic").set("nuvector", new String[]{"nu_m", "nu_m", "nu_m"});
    model.material("mat1").propertyGroup("Orthotropic").set("Gvector", new String[]{"G_m", "G_m", "G_m"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"rho_m"});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", new String[]{"alpha_m"});
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().set(1);
    model.component("comp1").material("matlnk2").label("\u6750\u6599\u94fe\u63a5 2\uff1a\u78b3\u7ea4\u7ef4");
    model.component("comp1").material("matlnk2").selection().named("geom1_pi1_cyl1_dom");
    model.material().create("mat2", "Common", "");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.material("mat2").label("\u6750\u6599 2\uff1a\u78b3\u7ea4\u7ef4");
    model.material("mat2").propertyGroup().create("Orthotropic", "Orthotropic", "Orthotropic");
    model.material("mat2").propertyGroup("Orthotropic").set("Evector", new String[]{"E1_f", "E2_f", "E2_f"});
    model.material("mat2").propertyGroup("Orthotropic").set("nuvector", new String[]{"nu12_f", "nu23_f", "nu12_f"});
    model.material("mat2").propertyGroup("Orthotropic").set("Gvector", new String[]{"G12_f", "G23_f", "G12_f"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha1_f", "alpha2_f", "alpha2_f"});
    model.material().create("effmat1", "Effective", "");
    model.material("effmat1").feature("const1").set("link", "mat1");
    model.material("effmat1").feature().create("const2", "ConstituentLink", "");
    model.material("effmat1").feature("const2").set("link", "mat2");
    model.material("effmat1").feature("const2").set("Vf", "v_f");
    model.material("effmat1").label("\u6709\u6548\u6750\u6599\uff1aVoigt-Reuss \u6a21\u578b (ROM)");
    model.material("effmat1").propertyGroup("def").setMixingRule("thermalexpansioncoefficient", "voigt_reuss_model");
    model.material("effmat1").propertyGroup("Orthotropic").setMixingRule("Evector", "voigt_reuss_model");
    model.material("effmat1").propertyGroup("Orthotropic").setMixingRule("nuvector", "voigt_reuss_model");
    model.material("effmat1").propertyGroup("Orthotropic").setMixingRule("Gvector", "voigt_reuss_model");
    model.material().duplicate("effmat2", "effmat1");
    model.material("effmat2")
         .label("\u6709\u6548\u6750\u6599\uff1a\u4fee\u6b63\u7684 Voigt-Reuss \u6a21\u578b (ROM)");
    model.material("effmat2").propertyGroup("Orthotropic").setMixingRule("Evector", "modified_voigt_reuss_model");
    model.material("effmat2").propertyGroup("Orthotropic").setMixingRule("nuvector", "modified_voigt_reuss_model");
    model.material("effmat2").propertyGroup("Orthotropic").setMixingRule("Gvector", "modified_voigt_reuss_model");
    model.material().duplicate("effmat3", "effmat2");
    model.material("effmat3").label("\u6709\u6548\u6750\u6599\uff1aChamis \u6a21\u578b (ROM)");
    model.material("effmat3").propertyGroup("def").setMixingRule("thermalexpansioncoefficient", "chamis_model");
    model.material("effmat3").propertyGroup("Orthotropic").setMixingRule("Evector", "chamis_model");
    model.material("effmat3").propertyGroup("Orthotropic").setMixingRule("nuvector", "chamis_model");
    model.material("effmat3").propertyGroup("Orthotropic").setMixingRule("Gvector", "chamis_model");
    model.material().duplicate("effmat4", "effmat3");
    model.material("effmat4").label("\u6709\u6548\u6750\u6599\uff1aHalpin-Tsai \u6a21\u578b (ROM)");
    model.material("effmat4").propertyGroup("Orthotropic").setMixingRule("Evector", "halpin_tsai_model");
    model.material("effmat4").set("zetaHT_Evector", new String[]{"inf", "0", "0"});
    model.material("effmat4").propertyGroup("Orthotropic").setMixingRule("nuvector", "halpin_tsai_model");
    model.material("effmat4").propertyGroup("Orthotropic").setMixingRule("Gvector", "halpin_tsai_model");
    model.material("effmat4").set("zetaHT_Gvector", new String[]{"1", "1", "1"});
    model.material().duplicate("effmat5", "effmat4");
    model.material("effmat5").label("\u6709\u6548\u6750\u6599\uff1aHalpin-Tsai-Nielsen \u6a21\u578b (ROM)");
    model.material("effmat5").propertyGroup("Orthotropic").setMixingRule("Evector", "halpin_tsai_nielsen_model");
    model.material("effmat5").set("htnMaxPackingPar", "0.82");
    model.material("effmat5").set("zetaHT_Evector", new String[]{"inf", "0", "0"});
    model.material("effmat5").propertyGroup("Orthotropic").setMixingRule("nuvector", "halpin_tsai_nielsen_model");
    model.material("effmat5").propertyGroup("Orthotropic").setMixingRule("Gvector", "halpin_tsai_nielsen_model");
    model.material("effmat5").set("htnMaxPackingPar", "0.82");
    model.material("effmat5").set("zetaHT_Gvector", new String[]{"1", "1", "1"});
    model.material("effmat5").propertyGroup("Orthotropic").setMixingRule("eta_Evector", "volume_average");
    model.material().duplicate("effmat6", "effmat5");
    model.material("effmat6").label("\u6709\u6548\u6750\u6599\uff1aHashin-Rosen \u6a21\u578b (ROM)");
    model.material("effmat6").propertyGroup("Orthotropic").setMixingRule("Evector", "hashin_rosen_model");
    model.material("effmat6").propertyGroup("Orthotropic").setMixingRule("nuvector", "hashin_rosen_model");
    model.material("effmat6").propertyGroup("Orthotropic").setMixingRule("Gvector", "hashin_rosen_model");
    model.material("effmat6").propertyGroup("Orthotropic").setMixingRule("eta_Evector", "volume_average");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 5);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run("swe1");

    model.study("solidcp1std").label("\u5f39\u6027\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027\u7814\u7a76");
    model.study("solidcp1std").feature("solidcp1stat").set("useadvanceddisable", true);
    model.study("solidcp1std").feature("solidcp1stat")
         .set("disabledphysics", new String[]{"solid/lemm1/te1", "solid/cp2"});
    model.study("solidcp1std").createAutoSequences("all");

    model.batch("solidcp1p").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").setIndex("looplevel", 7, 1);
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
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset2");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u5f39\u6027\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027\u7814\u7a76)");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevescp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("expr", "root.comp1.solid.cp1.D");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("descr", "\u5f39\u6027\u77e9\u9635");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result("pg1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").run();

    model.study("std1").label("\u70ed\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027\u7814\u7a76");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("param").setIndex("pname", "para", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "para", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std1").feature("param").setIndex("pname", "l", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "l", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "v_f", 1);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.1,0.1,0.7)", 1);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/cp1"});
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol9");
    model.sol("sol9").study("std1");
    model.sol("sol9").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol9");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevel", 7, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").label("\u5e94\u529b (solid) 1");
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
    model.result().evaluationGroup().create("std1Eg", "EvaluationGroup");
    model.result().evaluationGroup("std1Eg").set("data", "dset4");
    model.result().evaluationGroup("std1Eg").set("includeparameters", "off");
    model.result().evaluationGroup("std1Eg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std1Eg").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("std1Eg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u70ed\u5c5e\u6027\u7684\u5355\u5143\u5468\u671f\u6027\u7814\u7a76)");
    model.result().evaluationGroup("std1Eg").create("gmevtcp2", "EvalGlobalMatrix");
    model.result().evaluationGroup("std1Eg").feature("gmevtcp2").set("expr", "root.comp1.solid.cp2.alpha");
    model.result().evaluationGroup("std1Eg").feature("gmevtcp2").set("descr", "\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.result().evaluationGroup("std1Eg").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevelinput", "first", 0);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "v<sub>f</sub>");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "E<sub>1</sub>/E<sub>m</sub>");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "solid.cp1.D11/E_m", 0);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "effmat1.Orthotropic.Evector1/E_m", 1);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "effmat2.Orthotropic.Evector1/E_m", 2);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf", 2);
    model.result("pg3").feature("glob1").setIndex("expr", "effmat3.Orthotropic.Evector1/E_m", 3);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf", 3);
    model.result("pg3").feature("glob1").setIndex("expr", "effmat4.Orthotropic.Evector1/E_m", 4);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf", 4);
    model.result("pg3").feature("glob1").setIndex("expr", "effmat5.Orthotropic.Evector1/E_m", 5);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf", 5);
    model.result("pg3").feature("glob1").setIndex("expr", "effmat6.Orthotropic.Evector1/E_m", 6);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u6768\u6c0f\u6a21\u91cf", 6);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").feature("glob1").set("markerpos", "interp");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u5355\u5143\u5468\u671f\u6027", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "Voigt-Reuss \u6a21\u578b (ROM)", 1);
    model.result("pg3").feature("glob1").setIndex("legends", "\u4fee\u6b63\u7684 Voigt-Reuss \u6a21\u578b (ROM)", 2);
    model.result("pg3").feature("glob1").setIndex("legends", "Chamis \u6a21\u578b (ROM)", 3);
    model.result("pg3").feature("glob1").setIndex("legends", "Halpin-Tsai \u6a21\u578b (ROM)", 4);
    model.result("pg3").feature("glob1").setIndex("legends", "Halpin-Tsai-Nielsen \u6a21\u578b (ROM)", 5);
    model.result("pg3").feature("glob1").setIndex("legends", "Hashin-Rosen \u6a21\u578b (ROM)", 6);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg4")
         .set("title", "\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg4").set("ylabel", "E<sub>2</sub>/E<sub>m</sub>");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "solid.cp1.D22/E_m", 0);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "effmat1.Orthotropic.Evector2/E_m", 1);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "effmat2.Orthotropic.Evector2/E_m", 2);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "effmat3.Orthotropic.Evector2/E_m", 3);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf", 3);
    model.result("pg4").feature("glob1").setIndex("expr", "effmat4.Orthotropic.Evector2/E_m", 4);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf", 4);
    model.result("pg4").feature("glob1").setIndex("expr", "effmat5.Orthotropic.Evector2/E_m", 5);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf", 5);
    model.result("pg4").feature("glob1").setIndex("expr", "effmat6.Orthotropic.Evector2/E_m", 6);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u6768\u6c0f\u6a21\u91cf", 6);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u9762\u5185\u526a\u5207\u6a21\u91cf vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg5")
         .set("title", "\u9762\u5185\u526a\u5207\u6a21\u91cf vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg5").set("ylabel", "G<sub>12</sub>/G<sub>m</sub>");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "solid.cp1.D44/G_m", 0);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u9762\u5185\u526a\u5207\u6a21\u91cf", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "effmat1.Orthotropic.Gvector1/G_m", 1);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u9762\u5185\u526a\u5207\u6a21\u91cf", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "effmat2.Orthotropic.Gvector1/G_m", 2);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u9762\u5185\u526a\u5207\u6a21\u91cf", 2);
    model.result("pg5").feature("glob1").setIndex("expr", "effmat3.Orthotropic.Gvector1/G_m", 3);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u9762\u5185\u526a\u5207\u6a21\u91cf", 3);
    model.result("pg5").feature("glob1").setIndex("expr", "effmat4.Orthotropic.Gvector1/G_m", 4);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u9762\u5185\u526a\u5207\u6a21\u91cf", 4);
    model.result("pg5").feature("glob1").setIndex("expr", "effmat5.Orthotropic.Gvector1/G_m", 5);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u9762\u5185\u526a\u5207\u6a21\u91cf", 5);
    model.result("pg5").feature("glob1").setIndex("expr", "effmat6.Orthotropic.Gvector1/G_m", 6);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u9762\u5185\u526a\u5207\u6a21\u91cf", 6);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg6").label("\u7eb5\u5411\u70ed\u81a8\u80c0\u7cfb\u6570 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u7eb5\u5411\u70ed\u81a8\u80c0\u7cfb\u6570 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "v<sub>f</sub>");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\\alpha<sub>1</sub>/\\alpha<sub>m</sub>");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"solid.cp2.alphaXX"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u70ed\u81a8\u80c0\u7cfb\u6570\uff0cXX \u5206\u91cf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"1/K"});
    model.result("pg6").feature("glob1").setIndex("expr", "solid.cp2.alphaXX/alpha_m", 0);
    model.result("pg6").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u70ed\u81a8\u80c0\u7cfb\u6570", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "effmat1.def.alpha11/alpha_m", 1);
    model.result("pg6").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u70ed\u81a8\u80c0\u7cfb\u6570", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "effmat3.def.alpha11/alpha_m", 2);
    model.result("pg6").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u7eb5\u5411\u70ed\u81a8\u80c0\u7cfb\u6570", 2);
    model.result("pg6").feature("glob1").set("linemarker", "cycle");
    model.result("pg6").feature("glob1").set("markerpos", "interp");
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "\u5355\u5143\u5468\u671f\u6027", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "Voigt-Reuss \u6a21\u578b (ROM)", 1);
    model.result("pg6").feature("glob1").setIndex("legends", "Chamis \u6a21\u578b (ROM)", 2);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6a2a\u5411\u70ed\u81a8\u80c0\u7cfb\u6570 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg7")
         .set("title", "\u6a2a\u5411\u70ed\u81a8\u80c0\u7cfb\u6570 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg7").set("ylabel", "\\alpha<sub>2</sub>/\\alpha<sub>m</sub>");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "solid.cp2.alphaYY/alpha_m", 0);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u70ed\u81a8\u80c0\u7cfb\u6570", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "effmat1.def.alpha22/alpha_m", 1);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u70ed\u81a8\u80c0\u7cfb\u6570", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "effmat3.def.alpha22/alpha_m", 2);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u6a2a\u5411\u70ed\u81a8\u80c0\u7cfb\u6570", 2);
    model.result("pg7").run();
    model.result("pg6").run();
    model.result().duplicate("pg8", "pg6");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg8")
         .label("\u6cca\u677e\u6bd4\u4e0d\u4e3a\u96f6\u7684\u60c5\u51b5\u4e0b\u7eb5\u5411\u70ed\u81a8\u80c0\u7cfb\u6570\u4e0e\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570\u7684\u5173\u7cfb");
    model.result("pg8")
         .set("title", "\u6cca\u677e\u6bd4\u4e0d\u4e3a\u96f6\u7684\u60c5\u51b5\u4e0b\u7eb5\u5411\u70ed\u81a8\u80c0\u7cfb\u6570\u4e0e\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570\u7684\u5173\u7cfb");
    model.result("pg8").run();
    model.result("pg7").run();
    model.result().duplicate("pg9", "pg7");
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg9")
         .label("\u6cca\u677e\u6bd4\u4e0d\u4e3a\u96f6\u7684\u60c5\u51b5\u4e0b\u6a2a\u5411\u70ed\u81a8\u80c0\u7cfb\u6570\u4e0e\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570\u7684\u5173\u7cfb");
    model.result("pg9")
         .set("title", "\u6cca\u677e\u6bd4\u4e0d\u4e3a\u96f6\u7684\u60c5\u51b5\u4e0b\u6a2a\u5411\u70ed\u81a8\u80c0\u7cfb\u6570\u4e0e\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570\u7684\u5173\u7cfb");
    model.result("pg9").run();
    model.result("pg3").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");

    return model;
  }

  public static Model run2(Model model) {
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").label("\u5f39\u6027\u5c5e\u6027");

    model.result("pg6").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("grp2", 2);
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").label("\u70ed\u81a8\u80c0\u5c5e\u6027\uff0c\u96f6\u6cca\u677e\u6bd4");

    model.result("pg8").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("grp3", 3);
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg9");
    model.nodeGroup("grp3").label("\u70ed\u81a8\u80c0\u5c5e\u6027\uff0c\u975e\u96f6\u6cca\u677e\u6bd4");

    model.result("pg3").run();

    model.title("\u7ea4\u7ef4\u590d\u5408\u6750\u6599\u7684\u7ec6\u89c2\u529b\u5b66\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u5206\u6790\u7ea4\u7ef4\u590d\u5408\u6750\u6599\u57fa\u672c\u5355\u5143\u7684\u7b80\u5316\u7ec6\u89c2\u529b\u5b66\u6a21\u578b\uff0c\u6839\u636e\u7ea4\u7ef4\u548c\u57fa\u4f53\u5404\u81ea\u7684\u5c5e\u6027\uff0c\u8ba1\u7b97\u590d\u5408\u6750\u6599\u7684\u5747\u5300\u5f39\u6027\u548c\u70ed\u5c5e\u6027\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u57fa\u4e8e\u6df7\u5408\u89c4\u5219\u5f97\u5230\u7684\u503c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
    model.sol("solidcp1solp").clearSolutionData();
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
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();

    model.label("micromechanical_model_of_a_fiber_composite.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
