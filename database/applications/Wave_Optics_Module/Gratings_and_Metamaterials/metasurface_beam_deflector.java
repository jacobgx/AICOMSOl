/*
 * metasurface_beam_deflector.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:39 by COMSOL 6.3.0.290. */
public class metasurface_beam_deflector {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1.55[um]", "\u8bbe\u8ba1\u6ce2\u957f");
    model.param().set("px", "500[nm]", "x \u65b9\u5411\u7684\u57fa\u672c\u5355\u5143\u95f4\u8ddd");
    model.param().set("py", "500[nm]", "y \u65b9\u5411\u7684\u57fa\u672c\u5355\u5143\u95f4\u8ddd");
    model.param().set("tSub", "extraSpace/nSiO2", "\u57fa\u677f\u539a\u5ea6");
    model.param().set("tPost", "1[um]", "\u67f1\u539a\u5ea6");
    model.param().set("tAir", "tPost+extraSpace", "\u7a7a\u6c14\u539a\u5ea6");
    model.param()
         .set("extraSpace", "lda0/2", "\u8bf7\u786e\u4fdd\u67f1\u7684\u4e0a\u65b9/\u4e0b\u65b9\u6709\u8db3\u591f\u7684\u7a7a\u95f4");
    model.param().set("nSiO2", "1.444", "\u8bbe\u8ba1\u6ce2\u957f\u4e0b\u7684\u57fa\u677f\u6298\u5c04\u7387");
    model.param().set("N", "6", "\u57fa\u672c\u5355\u5143\u6570");
    model.param().set("d", "N*px", "x \u65b9\u5411\u7684\u7ed3\u6784\u5c3a\u5bf8");
    model.param().set("x1", "(0 + 1/2)*px", "\u67f1 1 \u7684\u4f4d\u7f6e");
    model.param().set("x2", "(1 + 1/2)*px", "\u67f1 2 \u7684\u4f4d\u7f6e");
    model.param().set("x3", "(2 + 1/2)*px", "\u67f1 3 \u7684\u4f4d\u7f6e");
    model.param().set("x4", "(3 + 1/2)*px", "\u67f1 4 \u7684\u4f4d\u7f6e");
    model.param().set("x5", "(4 + 1/2)*px", "\u67f1 5 \u7684\u4f4d\u7f6e");
    model.param().set("x6", "(5 + 1/2)*px", "\u67f1 6 \u7684\u4f4d\u7f6e");
    model.param().set("r1", "90[nm]", "\u67f1 1 \u7684\u534a\u5f84");
    model.param().set("r2", "130[nm]", "\u67f1 2 \u7684\u534a\u5f84");
    model.param().set("r3", "150[nm]", "\u67f1 3 \u7684\u534a\u5f84");
    model.param().set("r4", "165[nm]", "\u67f1 4 \u7684\u534a\u5f84");
    model.param().set("r5", "180[nm]", "\u67f1 5 \u7684\u534a\u5f84");
    model.param().set("r6", "195[nm]", "\u67f1 6 \u7684\u534a\u5f84");
    model.param().set("theta", "asin(lda0/d)/1[deg]", "\u504f\u6298\u89d2\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u57fa\u5e95");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"d", "py", "tSub"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-tSub"});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").label("\u7a7a\u6c14\u5757");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"d", "py", "tAir"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{0, 0, 0});
    model.component("comp1").geom("geom1").feature("blk2").set("selresultshow", false);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u7acb\u67f1 1");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r1");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "tPost");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"x1", "py/2", "0"});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7acb\u67f1");
    model.component("comp1").geom("geom1").feature("cyl1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u7acb\u67f1 2");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r2");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"x2", "py/2", "0"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl2");
    model.component("comp1").geom("geom1").feature("cyl3").label("\u7acb\u67f1 3");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "r3");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"x3", "py/2", "0"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").feature().duplicate("cyl4", "cyl3");
    model.component("comp1").geom("geom1").feature("cyl4").label("\u7acb\u67f1 4");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "r4");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"x4", "py/2", "0"});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").feature().duplicate("cyl5", "cyl4");
    model.component("comp1").geom("geom1").feature("cyl5").label("\u7acb\u67f1 5");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "r5");
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new String[]{"x5", "py/2", "0"});
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").feature().duplicate("cyl6", "cyl5");
    model.component("comp1").geom("geom1").feature("cyl6").label("\u7acb\u67f1 6");
    model.component("comp1").geom("geom1").feature("cyl6").set("r", "r6");
    model.component("comp1").geom("geom1").feature("cyl6").set("pos", new String[]{"x6", "py/2", "0"});
    model.component("comp1").geom("geom1").run("cyl6");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"blk2"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature().move("difsel1", 9);
    model.component("comp1").geom("geom1").run("difsel1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat1")
         .label("Si (Silicon) (Pierce and Spicer 1972: a-Si; n,k 0.0103-2.07 um)");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"1.033E-1", "3.27E-1"}, 
         {"1.078E-1", "3.63E-1"}, 
         {"1.127E-1", "3.92E-1"}, 
         {"1.181E-1", "4.23E-1"}, 
         {"1.240E-1", "4.59E-1"}, 
         {"1.305E-1", "4.97E-1"}, 
         {"1.378E-1", "5.43E-1"}, 
         {"1.459E-1", "5.97E-1"}, 
         {"1.550E-1", "6.60E-1"}, 
         {"1.653E-1", "7.35E-1"}, 
         {"1.771E-1", "8.32E-1"}, 
         {"1.907E-1", "9.51E-1"}, 
         {"2.066E-1", "1.11E+0"}, 
         {"2.254E-1", "1.35E+0"}, 
         {"2.480E-1", "1.69E+0"}, 
         {"2.583E-1", "1.86E+0"}, 
         {"2.695E-1", "2.07E+0"}, 
         {"2.818E-1", "2.30E+0"}, 
         {"2.952E-1", "2.56E+0"}, 
         {"3.100E-1", "2.87E+0"}, 
         {"3.263E-1", "3.21E+0"}, 
         {"3.444E-1", "3.55E+0"}, 
         {"3.543E-1", "3.73E+0"}, 
         {"3.647E-1", "3.90E+0"}, 
         {"3.875E-1", "4.17E+0"}, 
         {"4.133E-1", "4.38E+0"}, 
         {"4.428E-1", "4.47E+0"}, 
         {"4.769E-1", "4.49E+0"}, 
         {"4.960E-1", "4.47E+0"}, 
         {"5.166E-1", "4.46E+0"}, 
         {"5.636E-1", "4.36E+0"}, 
         {"6.199E-1", "4.23E+0"}, 
         {"6.526E-1", "4.17E+0"}, 
         {"6.888E-1", "4.09E+0"}, 
         {"7.293E-1", "4.01E+0"}, 
         {"7.749E-1", "3.93E+0"}, 
         {"8.266E-1", "3.86E+0"}, 
         {"8.856E-1", "3.77E+0"}, 
         {"9.538E-1", "3.68E+0"}, 
         {"1.033E+0", "3.61E+0"}, 
         {"1.127E+0", "3.57E+0"}, 
         {"1.240E+0", "3.54E+0"}, 
         {"1.378E+0", "3.50E+0"}, 
         {"1.550E+0", "3.48E+0"}, 
         {"1.771E+0", "3.45E+0"}, 
         {"2.066E+0", "3.44E+0"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"1.033E-1", "7.26E-1"}, 
         {"1.078E-1", "8.47E-1"}, 
         {"1.127E-1", "9.46E-1"}, 
         {"1.181E-1", "1.04E+0"}, 
         {"1.240E-1", "1.14E+0"}, 
         {"1.305E-1", "1.24E+0"}, 
         {"1.378E-1", "1.35E+0"}, 
         {"1.459E-1", "1.47E+0"}, 
         {"1.550E-1", "1.60E+0"}, 
         {"1.653E-1", "1.74E+0"}, 
         {"1.771E-1", "1.89E+0"}, 
         {"1.907E-1", "2.07E+0"}, 
         {"2.066E-1", "2.28E+0"}, 
         {"2.254E-1", "2.51E+0"}, 
         {"2.480E-1", "2.76E+0"}, 
         {"2.583E-1", "2.85E+0"}, 
         {"2.695E-1", "2.93E+0"}, 
         {"2.818E-1", "2.99E+0"}, 
         {"2.952E-1", "3.04E+0"}, 
         {"3.100E-1", "3.06E+0"}, 
         {"3.263E-1", "3.00E+0"}, 
         {"3.444E-1", "2.88E+0"}, 
         {"3.543E-1", "2.79E+0"}, 
         {"3.647E-1", "2.66E+0"}, 
         {"3.875E-1", "2.38E+0"}, 
         {"4.133E-1", "2.02E+0"}, 
         {"4.428E-1", "1.64E+0"}, 
         {"4.769E-1", "1.28E+0"}, 
         {"4.960E-1", "1.12E+0"}, 
         {"5.166E-1", "9.69E-1"}, 
         {"5.636E-1", "6.90E-1"}, 
         {"6.199E-1", "4.61E-1"}, 
         {"6.526E-1", "3.63E-1"}, 
         {"6.888E-1", "2.71E-1"}, 
         {"7.293E-1", "1.99E-1"}, 
         {"7.749E-1", "1.36E-1"}, 
         {"8.266E-1", "8.12E-2"}, 
         {"8.856E-1", "4.01E-2"}, 
         {"9.538E-1", "0.00E+0"}, 
         {"1.033E+0", "0.00E+0"}, 
         {"1.127E+0", "0.00E+0"}, 
         {"1.240E+0", "0.00E+0"}, 
         {"1.378E+0", "0.00E+0"}, 
         {"1.550E+0", "0.00E+0"}, 
         {"1.771E+0", "0.00E+0"}, 
         {"2.066E+0", "0.00E+0"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat2")
         .label("SiO2 (Silicon dioxide, Silica, Quartz) (Malitson 1965: Fused silica; n 0.21-6.7 um)");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"0.6961663", "0.4079426", "0.8974794", "0.00467914825849", "0.013512063073959999", "97.93400253792099"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard").set("Prefsma", "0");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat2").selection().named("geom1_blk1_dom");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u7a7a\u6c14");
    model.component("comp1").material("mat3").selection().named("geom1_difsel1");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});

    model.study("std1").feature("wave").set("plist", "lda0");

    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 3);
    model.component("comp1").physics("ewfd").feature("ps1").selection("excitedPortSelection").set(3);
    model.component("comp1").physics("ewfd").feature("ps1")
         .set("DiffractionOrderSpecification", "FromCurrentParameters");
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc1")
         .set("splitPeriodicConditionSelections", false);
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc2")
         .set("splitPeriodicConditionSelections", false);
    model.component("comp1").physics("ewfd").feature("ps1").feature("pport1").runCommand("addDiffractionOrders");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1")
         .label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1")
         .set("expr", new String[]{"ewfd.Rorder_0_0", "ewfd.Rorder_n2_0_ip", "ewfd.Rorder_n2_0_op", "ewfd.Rorder_n1_0_ip", "ewfd.Rorder_n1_0_op", "ewfd.Rorder_0_0_orth", "ewfd.Rorder_p1_0_ip", "ewfd.Rorder_p1_0_op", "ewfd.Rorder_p2_0_ip", "ewfd.Rorder_p2_0_op", 
         "ewfd.Rtotal", "ewfd.Torder_0_0", "ewfd.Torder_n1_0_ip", "ewfd.Torder_n1_0_op", "ewfd.Torder_0_0_orth", "ewfd.Torder_p1_0_ip", "ewfd.Torder_p1_0_op", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u6781\u5316\u56fe (ewfd)");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", "1", 0);
    model.result("pg2").create("plz1", "Polarization");
    model.result("pg2").feature("plz1").set("linestyle", "solid");
    model.result("pg2").feature("plz1").set("linewidth", 2);
    model.result("pg2").feature("plz1").set("display", "2");
    model.result("pg2").feature("plz1").create("col1", "Color");
    model.result("pg2").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg2").feature("plz1").set("legend", true);
    model.result("pg2").feature("plz1").set("legendmethod", "manual");
    model.result("pg2").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg2").create("plz2", "Polarization");
    model.result("pg2").feature("plz2").set("linestyle", "solid");
    model.result("pg2").feature("plz2").set("linewidth", 2);
    model.result("pg2").feature("plz2").set("display", "0");
    model.result("pg2").feature("plz2").create("col1", "Color");
    model.result("pg2").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg2").create("plz3", "Polarization");
    model.result("pg2").feature("plz3").set("linestyle", "solid");
    model.result("pg2").feature("plz3").set("linewidth", 2);
    model.result("pg2").feature("plz3").set("display", "1");
    model.result("pg2").feature("plz3").create("col1", "Color");
    model.result("pg2").feature("plz3").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz3").feature("col1").set("colorlegend", false);
    model.result("pg2").create("plz4", "Polarization");
    model.result("pg2").feature("plz4").set("linestyle", "solid");
    model.result("pg2").feature("plz4").set("linewidth", 2);
    model.result("pg2").feature("plz4").set("display", "3");
    model.result("pg2").feature("plz4").create("col1", "Color");
    model.result("pg2").feature("plz4").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz4").feature("col1").set("colorlegend", false);
    model.result("pg2").create("plz5", "Polarization");
    model.result("pg2").feature("plz5").set("linestyle", "solid");
    model.result("pg2").feature("plz5").set("linewidth", 2);
    model.result("pg2").feature("plz5").set("display", "4");
    model.result("pg2").feature("plz5").create("col1", "Color");
    model.result("pg2").feature("plz5").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz5").feature("col1").set("colorlegend", false);
    model.result("pg2").create("plz6", "Polarization");
    model.result("pg2").feature("plz6").set("linestyle", "dashed");
    model.result("pg2").feature("plz6").set("linewidth", 2);
    model.result("pg2").feature("plz6").set("display", "6");
    model.result("pg2").feature("plz6").create("col1", "Color");
    model.result("pg2").feature("plz6").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz6").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("plz6").set("legend", true);
    model.result("pg2").feature("plz6").set("legendmethod", "manual");
    model.result("pg2").feature("plz6").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg2").create("plz7", "Polarization");
    model.result("pg2").feature("plz7").set("linestyle", "dashed");
    model.result("pg2").feature("plz7").set("linewidth", 2);
    model.result("pg2").feature("plz7").set("display", "5");
    model.result("pg2").feature("plz7").create("col1", "Color");
    model.result("pg2").feature("plz7").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz7").feature("col1").set("colorlegend", false);
    model.result("pg2").create("plz8", "Polarization");
    model.result("pg2").feature("plz8").set("linestyle", "dashed");
    model.result("pg2").feature("plz8").set("linewidth", 2);
    model.result("pg2").feature("plz8").set("display", "7");
    model.result("pg2").feature("plz8").create("col1", "Color");
    model.result("pg2").feature("plz8").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").feature("plz8").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("expr", "ewfd.Ey");
    model.result("pg1").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg1").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().dataset().create("arr1", "Array3D");
    model.result().dataset("arr1").set("fullsize", new int[]{3, 1, 1});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"ewfd.kPeriodicx", "0", "0"});
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u9635\u5217\u4e2d\u7684 Ey");
    model.result("pg3").set("data", "arr1");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "ewfd.Ey");
    model.result("pg3").feature("slc1").set("quickplane", "zx");
    model.result("pg3").feature("slc1").set("quickynumber", 1);
    model.result("pg3").feature("slc1").set("colortable", "WaveLight");
    model.result("pg3").feature("slc1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);

    model.view("view2").set("showgrid", false);
    model.view("view2").set("showaxisorientation", false);

    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("repeat", "iterations");
    model.result().export("anim1").set("iterations", 5);
    model.result().export("anim1").run();

    model.title("\u8d85\u8868\u9762\u5149\u675f\u504f\u8f6c\u5668");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u4e00\u79cd\u4f7f\u7528\u5f02\u5e38\u6298\u5c04\u7684\u8d85\u8868\u9762\u5149\u675f\u504f\u8f6c\u5668\u3002\u8be5\u7ed3\u6784\u672c\u8eab\u662f\u7531\u516d\u4e2a\u67f1\u4f53\uff08\u79f0\u4e3a\u5143\u5143\u7d20\uff09\u7ec4\u6210\u7684\u91cd\u590d\u9635\u5217\uff0c\u5355\u4e2a\u67f1\u4f53\u7684\u5468\u671f\u4e3a 500\u00a0nm\uff0c\u56e0\u6b64\u516d\u4e2a\u67f1\u4f53\u6784\u6210\u7684\u5b8c\u6574\u7ed3\u6784\u5bbd\u5ea6\u4e3a 3\u00a0um\uff0c\u67f1\u4f53\u9ad8\u5ea6\u4e3a 1\u00a0um\uff0c\u5c06\u5728 1.55\u00a0um \u7684\u81ea\u7531\u7a7a\u95f4\u6ce2\u957f\u4e0b\u8fd0\u884c\u3002\n\n\u5706\u67f1\u5f62\u67f1\u4f53\u91c7\u7528\u7845\u6750\u6599\uff0c\u800c\u57fa\u5e95\u6750\u6599\u5219\u4e3a\u4e8c\u6c27\u5316\u7845 (SiO2)\u3002\u8fd9\u79cd\u7ed3\u6784\u8bbe\u8ba1\u80fd\u591f\u4f7f\u4ee5\u6cd5\u7ebf\u5165\u5c04\u89d2\u7a7f\u8fc7\u57fa\u5e95\u7684\u5165\u5c04\u5149\u4ee5\u6307\u5b9a\u7684\u89d2\u5ea6\uff08\u5f02\u5e38\u6298\u5c04\u89d2\uff09\u8fdb\u884c\u6298\u5c04\u3002\n\n\u4e3a\u4e86\u4f7f\u7528 COMSOL \u6a21\u62df\u8fd9\u4e00\u73b0\u8c61\uff0c\u6211\u4eec\u5728 x \u548c y \u65b9\u5411\u5e94\u7528\u201c\u5468\u671f\u6027\u6761\u4ef6\u201d\u8fb9\u754c\uff0c\u5e76\u5728 z \u65b9\u5411\u7ed3\u5408\u4f7f\u7528\u5468\u671f\u6027\u7c7b\u578b\u7684\u201c\u7aef\u53e3\u201d\uff0c\u5176\u201c\u884d\u5c04\u7ea7\u201d\u5b50\u8282\u70b9\u5c06\u5145\u5206\u8003\u8651\u5149\u5728\u6bcf\u4e2a\u5141\u8bb8\u7684\u884d\u5c04\u65b9\u5411\u4e0a\u7684\u900f\u5c04\u548c\u53cd\u5c04\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("metasurface_beam_deflector.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
