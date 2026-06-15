/*
 * composite_multiscale.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:35 by COMSOL 6.3.0.290. */
public class composite_multiscale {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.param().label("\u51e0\u4f55\u5c5e\u6027");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("l", "10[mm]", "\u5355\u5143\u957f\u5ea6");
    model.param().set("V", "l^3", "\u5355\u5143\u4f53\u79ef");
    model.param().set("v_f", "0.6", "\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.param().set("V_f", "v_f*V", "\u7ea4\u7ef4\u4f53\u79ef");
    model.param().set("r_f", "sqrt(V_f/(pi*l))", "\u7ea4\u7ef4\u534a\u5f84");
    model.param().set("th_i", "r_f/100", "\u754c\u9762\u539a\u5ea6");
    model.param().set("V_i", "pi*((r_f+th_i)^2-r_f^2)*l", "\u754c\u9762\u4f53\u79ef");
    model.param().set("v_i", "V_i/V", "\u754c\u9762\u4f53\u79ef\u5206\u6570");
    model.param().set("v_m", "1-v_f-v_i", "\u57fa\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.param().set("th", "1[mm]", "\u8584\u7247\u539a\u5ea6");
    model.param().set("rc", "100[mm]", "\u5706\u67f1\u534a\u5f84");
    model.param().set("hc", "500[mm]", "\u5706\u67f1\u9ad8\u5ea6");
    model.param().set("xd", "0[mm]", "\u989d\u5916\u7ef4\u5ea6\u4f4d\u7f6e");
    model.param().create("par2");
    model.param("par2").label("\u5f39\u6027\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("E_i", "100[GPa]", "\u754c\u9762\u6750\u6599\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("nu_i", "0.2", "\u754c\u9762\u6750\u6599\u7684\u6cca\u677e\u6bd4");
    model.param("par2").set("rho_i", "1400[kg/m^3]", "\u754c\u9762\u6750\u6599\u7684\u5bc6\u5ea6");
    model.param().create("par3");
    model.param("par3").label("\u5f3a\u5ea6\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3")
         .set("Sigmats1_f", "2458[MPa]", "\u7ea4\u7ef4\u7684\u6297\u62c9\u5f3a\u5ea6\uff0c11 \u65b9\u5411");
    model.param("par3")
         .set("Sigmacs1_f", "1500[MPa]", "\u7ea4\u7ef4\u7684\u6297\u538b\u5f3a\u5ea6\uff0c11 \u65b9\u5411");
    model.param("par3").set("Sigmats_m", "103[MPa]", "\u57fa\u4f53\u7684\u6297\u62c9\u5f3a\u5ea6");
    model.param("par3").set("Sigmacs_m", "265[MPa]", "\u57fa\u4f53\u7684\u6297\u538b\u5f3a\u5ea6");
    model.param("par3").set("Sigman_i", "60[MPa]", "\u754c\u9762\u7684\u754c\u9762\u6cd5\u5411\u5f3a\u5ea6");
    model.param("par3").set("Sigmas_i", "180[MPa]", "\u754c\u9762\u7684\u754c\u9762\u526a\u5207\u5f3a\u5ea6");
    model.param("par3")
         .set("Sigmats1", "1500[MPa]", "\u94fa\u5c42\u7684\u6297\u62c9\u5f3a\u5ea6\uff0c11 \u65b9\u5411");
    model.param("par3")
         .set("Sigmats2", "50[MPa]", "\u94fa\u5c42\u7684\u6297\u62c9\u5f3a\u5ea6\uff0c22 \u65b9\u5411");
    model.param("par3")
         .set("Sigmats3", "Sigmats2", "\u94fa\u5c42\u7684\u6297\u62c9\u5f3a\u5ea6\uff0c33 \u65b9\u5411");
    model.param("par3")
         .set("Sigmacs1", "900[MPa]", "\u94fa\u5c42\u7684\u6297\u538b\u5f3a\u5ea6\uff0c11 \u65b9\u5411");
    model.param("par3")
         .set("Sigmacs2", "230[MPa]", "\u94fa\u5c42\u7684\u6297\u538b\u5f3a\u5ea6\uff0c22 \u65b9\u5411");
    model.param("par3")
         .set("Sigmacs3", "Sigmacs2", "\u94fa\u5c42\u7684\u6297\u538b\u5f3a\u5ea6\uff0c33 \u65b9\u5411");
    model.param("par3")
         .set("Sigmass23", "90[MPa]", "\u94fa\u5c42\u7684\u526a\u5207\u5f3a\u5ea6\uff0c23 \u65b9\u5411");
    model.param("par3")
         .set("Sigmass13", "Sigmass23", "\u94fa\u5c42\u7684\u526a\u5207\u5f3a\u5ea6\uff0c13 \u65b9\u5411");
    model.param("par3")
         .set("Sigmass12", "Sigmass23", "\u94fa\u5c42\u7684\u526a\u5207\u5f3a\u5ea6\uff0c12 \u65b9\u5411");
    model.param("par3")
         .set("epsilonts1", "0.1", "\u94fa\u5c42\u7684\u6781\u9650\u62c9\u4f38\u5e94\u53d8\uff0c11 \u65b9\u5411");
    model.param("par3")
         .set("epsiloncs1", "0.08", "\u94fa\u5c42\u7684\u6781\u9650\u538b\u5e94\u53d8\uff0c11 \u65b9\u5411");
    model.param("par3").set("Sigma1D", "2000[MPa]", "\u7ebf\u6027\u9000\u5316\u5e94\u529b");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Fiber_Composites\\unidirectional_fiber_square_packing.mph", new String[]{"part1"});

    model.component("comp1").label("\u7ec4\u4ef6\uff1a\u7ec6\u89c2\u529b\u5b66\uff08\u6750\u6599\u5c5e\u6027\uff09");

    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "df", "2*r_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wm", "l");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dm", "l");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hm", "l");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"-0.005[m]", "-0.005[m]", "0"});
    model.component("comp1").geom("geom1").feature("pi1").setIndex("displ", "-0.005[m]", 2);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 6, 7, 8, 9);
    model.component("comp1").geom("geom1").feature("sel1").label("\u754c\u9762\u9009\u62e9");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 2);
    model.component("comp1").physics("solid").feature("tl1").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("tl1").set("lth", "th_i");
    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("computeDensity", true);
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().named("geom1_pi1_unisel1");
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp2", "bp1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().named("geom1_pi1_unisel2");
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp3", "bp2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().named("geom1_pi1_unisel3");
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u6750\u6599\u94fe\u63a5 1\uff1a\u57fa\u4f53");
    model.component("comp1").material("matlnk1").selection().named("geom1_pi1_dif1_dom");
    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.material("mat1").label("Epoxy polymer");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1250[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"75E-6[1/K]", "0", "0", "0", "75E-6[1/K]", "0", "0", "0", "75E-6[1/K]"});
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("def").set("heatcapacity", "796[J/(kg*K)]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "3.25[GPa]");
    model.material("mat1").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.265");
    model.material("mat1").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("IsotropicStrengthParameters").label("Isotropic strength parameters");
    model.material("mat1").propertyGroup("IsotropicStrengthParameters").set("sigmat", "87.5[MPa]");
    model.material("mat1").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("matlnk1").set("link", "mat1");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("\u6750\u6599\u94fe\u63a5 2\uff1a\u7ea4\u7ef4");
    model.component("comp1").material("matlnk2").selection().named("geom1_pi1_cyl1_dom");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat2").label("T-300 carbon fiber");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").set("density", "1760[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.7E-6[1/K]", "0", "0", "0", "12E-6[1/K]", "0", "0", "0", "12E-6[1/K]"});
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("def").set("heatcapacity", "");
    model.material("mat2").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"230[GPa]", "15[GPa]"});
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.2", "0.0714"});
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Gvect1", "27[GPa]");
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").label("\u6750\u6599\u94fe\u63a5 3\uff1a\u754c\u9762");
    model.component("comp1").material("matlnk3").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk3").selection().named("geom1_sel1");
    model.material().create("mat3", "Common", "");
    model.component("comp1").material("matlnk3").set("link", "mat3");
    model.material("mat3").label("\u754c\u9762\u6750\u6599");
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat3").propertyGroup("Enu").set("E", new String[]{"E_i"});
    model.material("mat3").propertyGroup("Enu").set("nu", new String[]{"nu_i"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"rho_i"});

    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("geom1_pi1_sel1");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("geom1_pi1_sel2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_pi1_sel1");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcp1std")
         .label("\u7814\u7a76\uff1a\u7ec6\u89c2\u529b\u5b66\uff08\u6750\u6599\u5c5e\u6027\uff09");
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u5e94\u529b\uff0c\u8584\u5c42 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 2);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection().set(6, 7, 8, 9);
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("solidcp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset1");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u7814\u7a76\uff1a\u7ec6\u89c2\u529b\u5b66\uff08\u6750\u6599\u5c5e\u6027\uff09)");
    model.result().evaluationGroup("solidcp1stdEg").create("gevdcp1", "EvalGlobal");
    model.result().evaluationGroup("solidcp1stdEg").feature("gevdcp1")
         .setIndex("expr", "root.comp1.solid.cp1.rho", 0);
    model.result().evaluationGroup("solidcp1stdEg").feature("gevdcp1").set("descr", "\u5bc6\u5ea6");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevescp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("expr", "root.comp1.solid.cp1.D");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("descr", "\u5f39\u6027\u77e9\u9635");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u57fa\u672c\u5355\u5143");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "von Mises \u5e94\u529b (MPa)");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").feature("vol1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(1);
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
    model.result("pg1").feature("vol2").feature("sel1").selection().set(2);
    model.result("pg2").run();
    model.result("pg1").feature().copy("surf1", "pg2/surf1");
    model.result("pg2").feature().remove("surf1");
    model.result("pg1").feature("surf1").set("titletype", "auto");
    model.result("pg1").feature("surf1").set("coloring", "colortable");
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").feature("surf1").set("inheritplot", "vol1");
    model.result("pg1").feature("surf1").set("applytodatasetedgesplotarr", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature().remove("def");
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("arraydim", "1");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-l/4", 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-1.2*l", 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u57fa\u4f53", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "l", 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-1.2*l", 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u7ea4\u7ef4", 1, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "2.2*l", 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-1.2*l", 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u754c\u9762", 2, 3);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("paramindicator", "Load case 1");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").set("paramindicator", "Load case 2");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").set("paramindicator", "Load case 5");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").set("paramindicator", "Load case 6");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").label("\u7ec6\u89c2\u529b\u5b66\uff08\u6750\u6599\u5c5e\u6027\uff09");

    model.component("comp1").physics("solid").feature("cp1").runCommand("createMaterialbyValue");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").label("\u7ec4\u4ef6\uff1a\u5b8f\u89c2\u529b\u5b66\uff08\u5168\u5c40\u54cd\u5e94\uff09");

    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("type", "surface");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "rc");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "hc");
    model.component("comp2").geom("geom2").feature("cyl1").set("axistype", "x");
    model.component("comp2").geom("geom2").run("cyl1");

    model.component("comp2").view("view3").set("showgrid", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").coordSystem("sys2").set("mastercoordsystcomp", "1");

    model.component("comp2").physics().create("lshell", "LayeredShell", "geom2");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/lshell", false);

    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[90/0]_2");
    model.material("lmat1").setIndex("link", "solidcp1mat", 0);
    model.material("lmat1").setIndex("rotation", 90, 0);
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "solidcp1mat", 1);
    model.material("lmat1").setIndex("rotation", 90, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "solidcp1mat", 1);
    model.material("lmat1").setIndex("rotation", 90, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("rotation", 0, 1);
    model.component("comp2").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp2").material("llmat1").set("transform", "repeated");
    model.component("comp2").material("llmat1").set("repeated", 2);
    model.component("comp2").material("llmat1").set("widthRatio", 0.4);

    model.component("comp2").physics("lshell").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp2").physics("lshell").feature("lemm1").create("sf1", "Safety", 2);
    model.component("comp2").physics("lshell").feature("lemm1").feature("sf1")
         .set("FailureCriterion", "Hashin Orthotropic");
    model.component("comp2").physics("lshell").feature("lemm1").feature().duplicate("sf2", "sf1");
    model.component("comp2").physics("lshell").feature("lemm1").feature("sf2")
         .set("FailureCriterion", "Puck Orthotropic");
    model.component("comp2").physics("lshell").feature("lemm1").feature("sf2").set("ptl", 0.35);
    model.component("comp2").physics("lshell").feature("lemm1").feature("sf2").set("pcl", 0.3);

    model.material("solidcp1mat").propertyGroup()
         .create("OrthotropicStrengthParameters", "OrthotropicStrengthParameters", "Orthotropic_strength_parameters_in_Voigt_notation");
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmats", new String[]{"Sigmats1", "Sigmats2", "Sigmats3"});
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmacs", new String[]{"Sigmacs1", "Sigmacs2", "Sigmacs3"});
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmass", new String[]{"Sigmass23", "Sigmass13", "Sigmass12"});
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters")
         .set("epsilont1", new String[]{"epsilonts1"});
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters")
         .set("epsilonc1", new String[]{"epsiloncs1"});
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters")
         .set("Ef1", new String[]{"235[GPa]"});
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters").set("nuf12", new String[]{"0.2"});
    model.material("solidcp1mat").propertyGroup("OrthotropicStrengthParameters")
         .set("sigma1D", new String[]{"Sigma1D"});

    model.component("comp2").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp2").physics("lshell").feature("fix1").selection().set(9, 10, 11, 12);
    model.component("comp2").physics("lshell").create("bndl1", "BoundaryLoad", 1);
    model.component("comp2").physics("lshell").feature("bndl1").selection().set(1, 2, 4, 6);
    model.component("comp2").physics("lshell").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp2").physics("lshell").feature("bndl1").set("force", new String[]{"0", "0", "-5E4"});

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().all();
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().all();
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std1").label("\u7814\u7a76\uff1a\u5b8f\u89c2\u529b\u5b66\uff08\u5168\u5c40\u54cd\u5e94\uff09");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset3lshelllshl", "LayeredMaterial");
    model.result().dataset("dset3lshelllshl").set("data", "dset3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset3lshelllshl");
    model.result("pg2").label("\u5e94\u529b (lshell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg2").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg2").run();
    model.result().dataset("dset3lshelllshl").set("scale", 5);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "von Mises \u5e94\u529b (MPa)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").create("lss1", "LayeredMaterialSlice");
    model.result("pg3").feature("lss1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg3").feature("lss1").set("threshold", "manual");
    model.result("pg3").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("lss1").set("colortable", "Prism");
    model.result("pg3").feature("lss1").set("colortabletrans", "none");
    model.result("pg3").feature("lss1").set("colorscalemode", "linear");
    model.result("pg3").feature("lss1").set("locdef", "relative");
    model.result("pg3").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg3").feature("lss1").create("def", "Deform");
    model.result("pg3").feature("lss1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg3").run();
    model.result("pg3").label("von Mises \u5e94\u529b\uff0c\u5207\u9762");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("view", "new");
    model.result("pg3").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").run();
    model.result("pg3").feature("lss1").set("titletype", "manual");
    model.result("pg3").feature("lss1").set("title", "von Mises \u5e94\u529b (MPa)");
    model.result("pg3").feature("lss1").set("locdef", "interfaces");
    model.result("pg3").feature("lss1").set("slicedisplacement", "rectangular");
    model.result("pg3").feature("lss1").set("orientationrectangular", "zx");
    model.result("pg3").feature("lss1").set("xseparation", 0.3);
    model.result("pg3").feature("lss1").set("zseparation", 0.7);
    model.result("pg3").feature("lss1").set("showdescriptions", true);
    model.result("pg3").feature("lss1").set("descriptionseparation", 0.6);
    model.result("pg3").feature("lss1").set("resolution", "norefine");
    model.result("pg3").run();
    model.result("pg3").feature("lss1").feature("def").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().numerical().create("max1", "MaxSurface");
    model.result().numerical("max1").set("data", "dset3");
    model.result().numerical("max1").selection().all();
    model.result().numerical("max1").setIndex("expr", "lshell.atxd1(0,lshell.mises)", 0);
    model.result().numerical("max1").setIndex("descr", "\u5185\u5c42\u7684 von Mises \u5e94\u529b", 0);
    model.result().numerical("max1").setIndex("expr", "lshell.atxd1(lshell.d,lshell.mises)", 1);
    model.result().numerical("max1").setIndex("descr", "\u5916\u5c42\u7684 von Mises \u5e94\u529b", 1);
    model.result().numerical("max1").set("includepos", true);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u6700\u5927\u503c 1");
    model.result().numerical("max1").set("table", "tbl1");
    model.result().numerical("max1").setResult();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset3");
    model.result().dataset("cpt1").set("pointx", "hc");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", "-rc");
    model.result().dataset("cpt1").set("snapping", "boundary");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("thr1", "ThroughThickness");
    model.result("pg4").feature("thr1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg4").feature("thr1").set("legend", true);
    model.result("pg4").feature("thr1").set("posentry", "selection");
    model.result("pg4").feature("thr1").selection().geom("geom2", 0);
    model.result("pg4").feature("thr1").selection().set(1);
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg4").run();
    model.result("pg4").label("von Mises \u5e94\u529b\uff0c\u5168\u539a\u5ea6");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();
    model.result("pg4").feature("thr1").set("data", "cpt1");
    model.result("pg4").run();
    model.result().dataset().create("dset3lshelllshlsf", "LayeredMaterial");
    model.result().dataset("dset3lshelllshlsf").label("\u591a\u5c42\u6750\u6599 2 (\u5b89\u5168\u6027)");
    model.result().dataset("dset3lshelllshlsf").set("data", "dset3");
    model.result().dataset("dset3lshelllshlsf").set("evaluatein", "layermidplanes");
    model.result().dataset("dset3lshelllshlsf").set("scale", "10*0.057445626465380296");
    model.result().dataset("dset3lshelllshlsf")
         .set("defaultPlotIDs", new String[]{"failureIndexlemm1sf1|lshell", "failureIndexlemm1sf2|lshell"});
    model.result().dataset("dset3lshelllshlsf").label("\u591a\u5c42\u6750\u6599 2 (\u5b89\u5168\u6027)");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3lshelllshlsf");
    model.result("pg5").label("\u5931\u6548\u6307\u6570 (lshell)");
    model.result("pg5").label("\u5931\u6548\u6307\u6570 (\u5b89\u5168\u6027 1)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"lshell.lemm1.sf1.f_i"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Traffic");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("descractive", false);
    model.result("pg5").label("\u5931\u6548\u6307\u6570 (\u5b89\u5168\u6027 1)");
    model.result("pg5").run();
    model.result("pg5").label("Hashin \u5931\u6548\u6307\u6570");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Hashin \u5931\u6548\u6307\u6570");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result().dataset("dset3lshelllshlsf").set("scale", 12);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("mrkr1", "Marker");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg5").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg5").feature("surf1").feature("mrkr1").set("pointradius", 4);
    model.result("pg5").feature("surf1").feature("mrkr1").set("backgroundcolor", "gray");
    model.result("pg5").feature("surf1").feature("mrkr1").set("anchorpoint", "lowerright");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset3lshelllshlsf");
    model.result("pg6").label("\u5931\u6548\u6307\u6570 (lshell)");
    model.result("pg6").label("\u5931\u6548\u6307\u6570 (\u5b89\u5168\u6027 2)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"lshell.lemm1.sf2.f_i"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Traffic");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("descractive", false);
    model.result("pg6").label("\u5931\u6548\u6307\u6570 (\u5b89\u5168\u6027 2)");
    model.result("pg6").run();
    model.result("pg6").label("Puck \u5931\u6548\u6307\u6570");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "Puck \u5931\u6548\u6307\u6570");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("mrkr1", "Marker");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg6").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg6").feature("surf1").feature("mrkr1").set("pointradius", 4);
    model.result("pg6").feature("surf1").feature("mrkr1").set("backgroundcolor", "gray");
    model.result("pg6").feature("surf1").feature("mrkr1").set("anchorpoint", "lowerright");
    model.result("pg6").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("Hashin \u5931\u6548\u6307\u6570\uff08\u7814\u7a76\uff1a\u5b8f\u89c2\u529b\u5b66\uff08\u5168\u5c40\u54cd\u5e94\uff09\uff09");
    model.result().evaluationGroup("eg1").set("data", "dset3lshelllshlsf");
    model.result().evaluationGroup("eg1").create("max1", "MaxVolume");
    model.result().evaluationGroup("eg1").feature("max1").set("expr", new String[]{"lshell.lemm1.sf1.f_ifT"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC", "lshell.lemm1.sf1.f_iiT"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u62c9\u4f38\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC", "lshell.lemm1.sf1.f_iiT", "lshell.lemm1.sf1.f_iiC"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u538b\u7f29\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2")
         .label("Puck \u5931\u6548\u6307\u6570\uff08\u7814\u7a76\uff1a\u5b8f\u89c2\u529b\u5b66\uff08\u5168\u5c40\u54cd\u5e94\uff09\uff09");
    model.result().evaluationGroup("eg2").set("data", "dset3lshelllshlsf");
    model.result().evaluationGroup("eg2").create("max1", "MaxVolume");
    model.result().evaluationGroup("eg2").feature("max1").set("expr", new String[]{"lshell.lemm1.sf2.f_ifT"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Puck \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf2.f_ifT", "lshell.lemm1.sf2.f_ifC"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Puck \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf2.f_ifT", "lshell.lemm1.sf2.f_ifC", "lshell.lemm1.sf2.f_imA"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Puck \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u95f4\u6a21\u5f0f A \u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf2.f_ifT", "lshell.lemm1.sf2.f_ifC", "lshell.lemm1.sf2.f_imA", "lshell.lemm1.sf2.f_imB"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Puck \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u95f4\u6a21\u5f0f A \u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u95f4\u6a21\u5f0f B \u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf2.f_ifT", "lshell.lemm1.sf2.f_ifC", "lshell.lemm1.sf2.f_imA", "lshell.lemm1.sf2.f_imB", "lshell.lemm1.sf2.f_imC"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Puck \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u95f4\u6a21\u5f0f A \u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u95f4\u6a21\u5f0f B \u5931\u6548\u6307\u6570", "Puck \u7ea4\u7ef4\u95f4\u6a21\u5f0f C \u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").run();
    model.result("pg2").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 2);
    model.nodeGroup("grp2").add("plotgroup", "pg2");
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u5b8f\u89c2\u529b\u5b66\uff08\u5168\u5c40\u54cd\u5e94\uff09");

    model.param().create("par4");
    model.param("par4").label("\u6750\u6599\u70b9\u4f4d\u7f6e");
    model.param("par4").set("X0", "0[m]");
    model.param("par4").descr("X0", "\u6750\u6599\u70b9\uff0cX \u5750\u6807");
    model.param("par4").set("Y0", "0[m]");
    model.param("par4").descr("Y0", "\u6750\u6599\u70b9\uff0cY \u5750\u6807");
    model.param("par4").set("Z0", "-rc");
    model.param("par4").descr("Z0", "\u6750\u6599\u70b9\uff0cZ \u5750\u6807");
    model.param("par4").paramCase().create("case1");
    model.param("par4").paramCase().duplicate("case2", "case1");
    model.param("par4").paramCase("case2").set("X0", "hc/2");
    model.param("par4").paramCase().duplicate("case3", "case2");
    model.param("par4").paramCase("case3").set("X0", "hc");
    model.param("par4").paramCase().duplicate("case4", "case3");
    model.param("par4").paramCase("case4").set("X0", "0[m]");
    model.param("par4").paramCase("case4").set("Z0", "rc");
    model.param("par4").paramCase().duplicate("case5", "case4");
    model.param("par4").paramCase("case5").set("X0", "hc/2");
    model.param("par4").paramCase().duplicate("case6", "case5");
    model.param("par4").paramCase("case6").set("X0", "hc");

    model.component().copy("comp3", "comp1");

    model.component("comp3").label("\u7ec4\u4ef6\uff1a\u7ec6\u89c2\u529b\u5b66\uff08\u5c40\u90e8\u54cd\u5e94\uff09");

    model.component("comp3").geom("geom3").run("pi1");
    model.component("comp3").geom("geom3").create("rot1", "Rotate");
    model.component("comp3").geom("geom3").feature("rot1").selection("input").set("pi1");
    model.component("comp3").geom("geom3").feature("rot1").set("rot", "if(xd==0,90[deg],0)");
    model.component("comp3").geom("geom3").run("rot1");

    model.component("comp3").coordSystem().create("sys4", "Rotated");

    model.component("comp3").geom("geom3").run();

    model.component("comp3").coordSystem("sys4").set("angle", new String[]{"if(xd==0,90[deg],0)", "0", "0"});
    model.component("comp3").coordSystem("sys3").set("mastersystem", "sys4");
    model.component("comp3").coordSystem("sys3").set("mastercoordsystcomp", "1");

    model.component("comp3").variable().create("var1");

//    To import content from file, use:
//    model.component("comp3").variable("var1").loadFile("FILENAME");
    model.component("comp3").variable("var1")
         .set("g_f", "if(solid2.sl11>=0,solid2.sl11/Sigmats1_f,-solid2.sl11/Sigmacs1_f)-1", "\u7ea4\u7ef4\u5931\u6548\u51c6\u5219");
    model.component("comp3").variable("var1")
         .set("sf_f", "if(solid2.sl11>=0,Sigmats1_f/solid2.sl11,Sigmacs1_f/-solid2.sl11)", "\u7ea4\u7ef4\u5931\u6548\u51c6\u5219\u7684\u5b89\u5168\u7cfb\u6570");
    model.component("comp3").variable("var1")
         .set("g_m", "solid2.mises^2/(Sigmats_m*Sigmacs_m)+(1/Sigmats_m-1/Sigmacs_m)*solid2.I1s-1", "\u57fa\u4f53\u5931\u6548\u51c6\u5219");
    model.component("comp3").variable("var1")
         .set("a", "solid2.mises^2/(Sigmats_m*Sigmacs_m)", "\u4e8c\u6b21\u65b9\u7a0b\u7684\u7cfb\u6570");
    model.component("comp3").variable("var1")
         .set("b", "(1/Sigmats_m-1/Sigmats_m)*solid2.I1s", "\u4e8c\u6b21\u65b9\u7a0b\u7684\u7cfb\u6570");
    model.component("comp3").variable("var1").set("c", "-1", "\u4e8c\u6b21\u65b9\u7a0b\u7684\u7cfb\u6570");
    model.component("comp3").variable("var1").set("r1", "(-b+sqrt(b^2-4*a*c))/(2*a)", "\u7b2c\u4e00\u4e2a\u6839");
    model.component("comp3").variable("var1").set("r2", "(-b-sqrt(b^2-4*a*c))/(2*a)", "\u7b2c\u4e8c\u4e2a\u6839");
    model.component("comp3").variable("var1")
         .set("sf_m", "r1*(r1>0)*(r2<=0)+r2*(r1<=0)*(r2>0)+min(r1,r2)*(r1>0)*(r2>0)", "\u57fa\u4f53\u5931\u6548\u51c6\u5219\u7684\u5b89\u5168\u7cfb\u6570");
    model.component("comp3").variable("var1")
         .set("Tax", "solid2.sxx*solid2.nx+solid2.sxy*solid2.ny+solid2.sxz*solid2.nz", "\u7275\u5f15\u529b\uff0cx \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("Tay", "solid2.sxy*solid2.nx+solid2.syy*solid2.ny+solid2.syz*solid2.nz", "\u7275\u5f15\u529b\uff0cy \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("Taz", "solid2.sxz*solid2.nx+solid2.syz*solid2.ny+solid2.szz*solid2.nz", "\u7275\u5f15\u529b\uff0cz \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("Ta1", "solid2.sysT11*Tax+solid2.sysT21*Tay+solid2.sysT31*Taz", "\u7275\u5f15\u529b\uff0c\u5207\u5411\u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("Ta2", "solid2.sysT12*Tax+solid2.sysT22*Tay+solid2.sysT32*Taz", "\u7275\u5f15\u529b\uff0c\u5468\u5411\u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("Ta3", "solid2.sysT13*Tax+solid2.sysT23*Tay+solid2.sysT33*Taz", "\u7275\u5f15\u529b\uff0c\u6cd5\u5411\u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("g_i", "(if(Ta3>=0,Ta3,0)/Sigman_i)^2+(Ta1/Sigmas_i)^2-1", "\u754c\u9762\u5931\u6548\u51c6\u5219");
    model.component("comp3").variable("var1")
         .set("sf_i", "1/sqrt(g_i+1)", "\u754c\u9762\u5931\u6548\u51c6\u5219\u7684\u5b89\u5168\u7cfb\u6570");
    model.component("comp3").variable("var1")
         .set("eXX", "comp2.at2(X0,Y0,Z0,comp2.lshell.atxd1(xd,comp2.lshell.eXX))", "\u5f39\u6027\u5e94\u53d8\uff0c\u6765\u81ea\u591a\u5c42\u58f3\u63a5\u53e3\u7684 XX \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("eYY", "comp2.at2(X0,Y0,Z0,comp2.lshell.atxd1(xd,comp2.lshell.eYY))", "\u5f39\u6027\u5e94\u53d8\uff0c\u6765\u81ea\u591a\u5c42\u58f3\u63a5\u53e3\u7684 YY \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("eZZ", "comp2.at2(X0,Y0,Z0,comp2.lshell.atxd1(xd,comp2.lshell.eZZ))", "\u5f39\u6027\u5e94\u53d8\uff0c\u6765\u81ea\u591a\u5c42\u58f3\u63a5\u53e3\u7684 ZZ \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("eXY", "comp2.at2(X0,Y0,Z0,comp2.lshell.atxd1(xd,comp2.lshell.eXY))", "\u5f39\u6027\u5e94\u53d8\uff0c\u6765\u81ea\u591a\u5c42\u58f3\u63a5\u53e3\u7684 XY \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("eYZ", "comp2.at2(X0,Y0,Z0,comp2.lshell.atxd1(xd,comp2.lshell.eYZ))", "\u5f39\u6027\u5e94\u53d8\uff0c\u6765\u81ea\u591a\u5c42\u58f3\u63a5\u53e3\u7684 YZ \u5206\u91cf");
    model.component("comp3").variable("var1")
         .set("eXZ", "comp2.at2(X0,Y0,Z0,comp2.lshell.atxd1(xd,comp2.lshell.eXZ))", "\u5f39\u6027\u5e94\u53d8\uff0c\u6765\u81ea\u591a\u5c42\u58f3\u63a5\u53e3\u7684 XZ \u5206\u91cf");

    model.component("comp3").physics("solid2").feature("lemm1").set("coordinateSystem", "sys4");
    model.component("comp3").physics("solid2").feature("lemm1").create("sf1", "Safety", 3);
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf1")
         .label("\u7ea4\u7ef4\u5931\u6548\u51c6\u5219");
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf1").selection().set(2);
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf1")
         .set("FailureCriterion", "User defined");
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf1").set("gSolid", "g_f");
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf1").set("rSolid", "sf_f");
    model.component("comp3").physics("solid2").feature("lemm1").create("sf2", "Safety", 3);
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf2")
         .label("\u57fa\u4f53\u5931\u6548\u51c6\u5219");
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf2").selection().set(1);
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf2")
         .set("FailureCriterion", "User defined");
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf2").set("gSolid", "g_m");
    model.component("comp3").physics("solid2").feature("lemm1").feature("sf2").set("rSolid", "sf_m");
    model.component("comp3").physics("solid2").feature("tl1").feature("lemm1").create("sf1", "Safety", 2);
    model.component("comp3").physics("solid2").feature("tl1").feature("lemm1").feature("sf1")
         .label("\u754c\u9762\u5931\u6548\u51c6\u5219");
    model.component("comp3").physics("solid2").feature("tl1").feature("lemm1").feature("sf1")
         .set("FailureCriterion", "User defined");
    model.component("comp3").physics("solid2").feature("tl1").feature("lemm1").feature("sf1").set("gSolid", "g_i");
    model.component("comp3").physics("solid2").feature("tl1").feature("lemm1").feature("sf1").set("rSolid", "sf_i");
    model.component("comp3").physics("solid2").feature("cp1").set("BoundaryExpansion", "Mixed");
    model.component("comp3").physics("solid2").feature("cp1").set("eavgi1", "eXX");
    model.component("comp3").physics("solid2").feature("cp1").set("eavgi2", "eYY");
    model.component("comp3").physics("solid2").feature("cp1").set("eavgi3", "eZZ");
    model.component("comp3").physics("solid2").feature("cp1").set("eavgi4", "eXY");
    model.component("comp3").physics("solid2").feature("cp1").set("eavgi5", "eYZ");
    model.component("comp3").physics("solid2").feature("cp1").set("eavgi6", "eXZ");

    model.component("comp3").mesh("mesh3").run("swe1");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").setSolveFor("/physics/lshell", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u7ec6\u89c2\u529b\u5b66\uff08\u5c40\u90e8\u54cd\u5e94\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").set("sweeptype", "switch");
    model.study("std2").feature("param").setIndex("switchname", "default", 0);
    model.study("std2").feature("param").setIndex("switchcase", "all", 0);
    model.study("std2").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std2").feature("param").setIndex("switchname", "default", 0);
    model.study("std2").feature("param").setIndex("switchcase", "all", 0);
    model.study("std2").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std2").feature("param").setIndex("switchname", "par4", 0);
    model.study("std2").create("param2", "Parametric");
    model.study("std2").feature("param2").setIndex("pname", "E_i", 0);
    model.study("std2").feature("param2").setIndex("plistarr", "", 0);
    model.study("std2").feature("param2").setIndex("punit", "Pa", 0);
    model.study("std2").feature("param2").setIndex("pname", "E_i", 0);
    model.study("std2").feature("param2").setIndex("plistarr", "", 0);
    model.study("std2").feature("param2").setIndex("punit", "Pa", 0);
    model.study("std2").feature("param2").setIndex("pname", "xd", 0);
    model.study("std2").feature("param2").setIndex("plistarr", "0 4*th", 0);
    model.study("std2").feature("param2").setIndex("punit", "mm", 0);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p2").run("compute");

    model.nodeGroup().duplicate("grp3", "grp1");
    model.nodeGroup("grp3").label("\u7ec6\u89c2\u529b\u5b66\uff08\u5c40\u90e8\u54cd\u5e94\uff09");

    model.result("pg7").run();
    model.result("pg7")
         .label("\u5e94\u529b\uff0c\u57fa\u672c\u5355\u5143\uff08\u5185\u5c42\u7b2c\u4e00\u4e2a\u6750\u6599\u70b9\u4e0a\uff09");
    model.result("pg7").set("data", "dset9");
    model.result("pg7").setIndex("looplevel", 1, 1);
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").set("paramindicator", "");
    model.result("pg7").feature("vol1").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").set("expr", "solid2.mises");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").feature("sel1").selection().set(1);
    model.result("pg7").feature("vol2").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("vol2").set("expr", "solid2.mises");
    model.result("pg7").run();
    model.result("pg7").feature("vol2").feature("sel1").selection().set(2);
    model.result("pg7").feature("surf1").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "solid2.mises");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("sel1").selection().set(6, 7, 8, 9);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");

    model.nodeGroup("grp3").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8")
         .label("\u5e94\u529b\uff0c\u57fa\u672c\u5355\u5143\uff08\u591a\u4e2a\u6750\u6599\u70b9\u4e0a\uff09");
    model.result("pg8").set("data", "dset9");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "von Mises \u5e94\u529b (MPa)");
    model.result("pg8").set("paramindicator", "");
    model.result("pg8").set("edges", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("data", "dset9");
    model.result("pg8").feature("surf1").setIndex("looplevel", 1, 1);
    model.result("pg8").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg8").feature("surf1").set("expr", "solid2.mises");
    model.result("pg8").feature("surf1").set("rangecoloractive", true);
    model.result("pg8").feature("surf1").set("rangecolormax", 100);
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").feature("surf1").set("resolution", "custom");
    model.result("pg8").feature("surf1").set("refine", 2);
    model.result("pg8").feature("surf1").set("threshold", "manual");
    model.result("pg8").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("surf1").create("trn1", "Transformation");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("trn1").set("move", new String[]{"X0", "Y0", "Z0+18*th"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").setIndex("looplevel", 2, 0);
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature().duplicate("surf3", "surf2");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").setIndex("looplevel", 3, 0);
    model.result("pg8").feature().duplicate("surf4", "surf3");
    model.result("pg8").run();
    model.result("pg8").feature("surf4").setIndex("looplevel", 4, 0);
    model.result("pg8").run();
    model.result("pg8").feature("surf4").feature("trn1").set("move", new String[]{"X0", "Y0", "Z0-18*th"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf5", "surf4");
    model.result("pg8").run();
    model.result("pg8").feature("surf5").setIndex("looplevel", 5, 0);
    model.result("pg8").feature().duplicate("surf6", "surf5");
    model.result("pg8").run();
    model.result("pg8").feature("surf6").setIndex("looplevel", 6, 0);
    model.result("pg8").feature().duplicate("surf7", "surf6");
    model.result("pg8").run();
    model.result("pg8").feature("surf7").setIndex("looplevel", 2, 1);
    model.result("pg8").feature("surf7").setIndex("looplevel", 1, 0);
    model.result("pg8").feature().duplicate("surf8", "surf7");
    model.result("pg8").run();
    model.result("pg8").feature("surf8").setIndex("looplevel", 2, 0);
    model.result("pg8").feature().duplicate("surf9", "surf8");
    model.result("pg8").run();
    model.result("pg8").feature("surf9").setIndex("looplevel", 3, 0);
    model.result("pg8").feature().duplicate("surf10", "surf9");
    model.result("pg8").run();
    model.result("pg8").feature("surf10").setIndex("looplevel", 4, 0);
    model.result("pg8").run();
    model.result("pg8").feature("surf10").feature("trn1").set("move", new String[]{"X0", "Y0", "Z0+18*th"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf11", "surf10");
    model.result("pg8").run();
    model.result("pg8").feature("surf11").setIndex("looplevel", 5, 0);
    model.result("pg8").feature().duplicate("surf12", "surf11");
    model.result("pg8").run();
    model.result("pg8").feature("surf12").setIndex("looplevel", 6, 0);
    model.result("pg8").run();
    model.result("pg8").create("line1", "Line");
    model.result("pg8").feature("line1").set("expr", "0");
    model.result("pg8").feature("line1").set("data", "dset3lshelllshlsf");
    model.result("pg8").feature("line1").set("coloring", "uniform");
    model.result("pg8").feature("line1").set("color", "black");
    model.result("pg8").run();
    model.result("pg8").set("view", "new");
    model.result("pg8").run();
    model.result("pg7").run();
    model.result().duplicate("pg9", "pg7");

    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9")
         .label("\u7528\u6237\u5b9a\u4e49\u7684\u5931\u6548\u6307\u6570\uff08\u5185\u5c42\u7b2c\u4e00\u4e2a\u6750\u6599\u70b9\u4e0a\uff09");
    model.result("pg9").set("title", "\u7528\u6237\u5b9a\u4e49\u7684\u5931\u6548\u6307\u6570 (1)");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature("vol1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("vol1").set("expr", "solid2.lemm1.sf2.f_i");
    model.result("pg9").feature("vol1").set("descr", "\u7528\u6237\u5b9a\u4e49\u5931\u6548\u6307\u6570");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg9").feature("vol2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("vol2").set("expr", "solid2.lemm1.sf1.f_i");
    model.result("pg9").feature("vol2").set("descr", "\u7528\u6237\u5b9a\u4e49\u5931\u6548\u6307\u6570");
    model.result("pg9").feature("surf1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "solid2.tl1.lemm1.sf1.f_i");
    model.result("pg9").feature("surf1").set("descr", "\u7528\u6237\u5b9a\u4e49\u5931\u6548\u6307\u6570");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");

    model.nodeGroup("grp3").add("plotgroup", "pg10");

    model.result("pg10").run();
    model.result("pg10")
         .label("\u7528\u6237\u5b9a\u4e49\u7684\u5931\u6548\u6307\u6570\uff08\u5916\u5c42\u7b2c\u4e00\u4e2a\u6750\u6599\u70b9\u4e0a\uff09");
    model.result("pg10").setIndex("looplevel", 2, 1);
    model.result("pg10").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3")
         .label("\u7528\u6237\u5b9a\u4e49\u7684\u5185\u5c42\u7b2c\u4e00\u4e2a\u6750\u6599\u70b9\u5931\u6548\u6307\u6570\uff08\u7814\u7a76\uff1a\u7ec6\u89c2\u529b\u5b66\uff08\u5c40\u90e8\u54cd\u5e94\uff09\uff09");
    model.result().evaluationGroup("eg3").set("data", "dset9");
    model.result().evaluationGroup("eg3").setIndex("looplevelinput", "first", 1);
    model.result().evaluationGroup("eg3").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg3").create("max1", "MaxVolume");
    model.result().evaluationGroup("eg3").feature("max1").selection().set(1);
    model.result().evaluationGroup("eg3").feature("max1").set("expr", new String[]{"solid2.lemm1.sf2.f_i"});
    model.result().evaluationGroup("eg3").feature("max1")
         .set("descr", new String[]{"\u7528\u6237\u5b9a\u4e49\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg3").feature("max1").set("unit", new String[]{"1"});
    model.result().evaluationGroup("eg3").feature("max1")
         .setIndex("descr", "\u7528\u6237\u5b9a\u4e49\u7684\u77e9\u9635\u5931\u6548\u6307\u6570", 0);
    model.result().evaluationGroup("eg3").create("max2", "MaxVolume");
    model.result().evaluationGroup("eg3").feature("max2").selection().set(2);
    model.result().evaluationGroup("eg3").feature("max2").set("expr", new String[]{"solid2.lemm1.sf1.f_i"});
    model.result().evaluationGroup("eg3").feature("max2")
         .set("descr", new String[]{"\u7528\u6237\u5b9a\u4e49\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg3").feature("max2").set("unit", new String[]{"1"});
    model.result().evaluationGroup("eg3").feature("max2")
         .setIndex("descr", "\u7528\u6237\u5b9a\u4e49\u7684\u7ea4\u7ef4\u5931\u6548\u6307\u6570", 0);
    model.result().evaluationGroup("eg3").create("max3", "MaxSurface");
    model.result().evaluationGroup("eg3").feature("max3").selection().named("geom3_sel1");
    model.result().evaluationGroup("eg3").feature("max3").set("expr", new String[]{"solid2.tl1.lemm1.sf1.f_i"});
    model.result().evaluationGroup("eg3").feature("max3")
         .set("descr", new String[]{"\u7528\u6237\u5b9a\u4e49\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg3").feature("max3").set("unit", new String[]{"1"});
    model.result().evaluationGroup("eg3").feature("max3")
         .setIndex("descr", "\u7528\u6237\u5b9a\u4e49\u7684\u754c\u9762\u5931\u6548\u6307\u6570", 0);
    model.result().evaluationGroup("eg3").set("transpose", true);
    model.result().evaluationGroup("eg3").set("includeparameters", false);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg3");
    model.result().evaluationGroup("eg4")
         .label("\u7528\u6237\u5b9a\u4e49\u7684\u5916\u5c42\u7b2c\u4e00\u4e2a\u6750\u6599\u70b9\u5931\u6548\u6307\u6570\uff08\u7814\u7a76\uff1a\u7ec6\u89c2\u529b\u5b66\uff08\u5c40\u90e8\u54cd\u5e94\uff09\uff09");
    model.result().evaluationGroup("eg4").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg4").run();

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("stat").setSolveFor("/physics/solid2", false);

    model.result("pg8").run();

    model
         .title("\u5931\u6548\u7684\u7ec6\u89c2\u529b\u5b66\uff1a\u590d\u5408\u6750\u6599\u7ed3\u6784\u7684\u591a\u5c3a\u5ea6\u5206\u6790");

    model
         .description("\u590d\u5408\u6750\u6599\u7684\u5206\u6790\u53ef\u4ee5\u4ece\u5b8f\u89c2\u6216\u5fae\u89c2\u5c3a\u5ea6\u4e24\u4e2a\u5c42\u9762\u5c55\u5f00\uff0c\u6bcf\u79cd\u5206\u6790\u90fd\u6709\u5176\u4f18\u52bf\u548c\u5c40\u9650\u6027\u3002\u5c06\u5b8f\u89c2\u4e0e\u5fae\u89c2\u5c3a\u5ea6\u76f8\u7ed3\u5408\u7684\u591a\u5c3a\u5ea6\u5206\u6790\uff0c\u80fd\u591f\u6df1\u5316\u6211\u4eec\u5bf9\u590d\u5408\u6750\u6599\u7ed3\u6784\u53ca\u5176\u7ec4\u6210\u90e8\u5206\u54cd\u5e94\u7684\u7406\u89e3\uff0c\u5e76\u6709\u52a9\u4e8e\u57fa\u4e8e\u5168\u5c40\u5c3a\u5ea6\u8f7d\u8377\u7814\u7a76\u5931\u6548\u7684\u7ec6\u89c2\u529b\u5b66\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u6211\u4eec\u901a\u8fc7\u5b8f\u89c2\u548c\u5fae\u89c2\u4e24\u4e2a\u5c3a\u5ea6\u8bc4\u4f30\u4e86\u7531\u7ea4\u7ef4\u590d\u5408\u6750\u6599\u5236\u6210\u7684\u5706\u67f1\u4f53\u7684\u7ed3\u6784\u5b8c\u6574\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
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

    model.label("composite_multiscale.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
