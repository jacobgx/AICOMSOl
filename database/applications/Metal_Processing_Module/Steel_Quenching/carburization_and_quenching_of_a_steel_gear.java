/*
 * carburization_and_quenching_of_a_steel_gear.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:06 by COMSOL 6.3.0.290. */
public class carburization_and_quenching_of_a_steel_gear {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Metal_Processing_Module\\Steel_Quenching");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("audc", "AusteniteDecomposition", "geom1");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("trip", "1");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("plasticity", "0");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("dilstrain", "1");
    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", "1");
    model.component("comp1").physics("audc").prop("ShapeProperty").set("order_straindiscr_disc", "2");
    model.component("comp1").physics().create("carb", "Carburization", "geom1");

    model.component("comp1").multiphysics().create("lht1", "PhaseTransformationLatentHeat", 2);
    model.component("comp1").multiphysics("lht1").set("Metphase_physics", "audc");
    model.component("comp1").multiphysics("lht1").set("HeatTransfer_physics", "ht");
    model.component("comp1").multiphysics("lht1").selection().all();
    model.component("comp1").multiphysics().create("ptstr1", "PhaseTransformationStrain", 2);
    model.component("comp1").multiphysics("ptstr1").set("Metphase_physics", "audc");
    model.component("comp1").multiphysics("ptstr1").set("SolidMechanics_physics", "solid");
    model.component("comp1").multiphysics("ptstr1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/audc", true);
    model.study("std1").feature("time").setSolveFor("/physics/carb", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/lht1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ptstr1", true);

    model.param().set("cenv", "0.75");
    model.param().descr("cenv", "\u78b3\u52bf");
    model.param().set("c0", "0.2");
    model.param().descr("c0", "\u521d\u59cb\u78b3\u6d53\u5ea6");
    model.param().set("Dc", "2e-7[cm^2/s]");
    model.param().descr("Dc", "\u78b3\u6269\u6563\u7cfb\u6570");
    model.param().set("kc", "2e-5[cm/s]");
    model.param().descr("kc", "\u8d28\u91cf\u4f20\u9012\u7cfb\u6570");

    model.geom()
         .load(new String[]{"part1"}, "Multibody_Dynamics_Module\\2D\\External_Gears\\spur_gear_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 0.06);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("sq2").set("size", 0.06);
    model.component("comp1").geom("geom1").feature("sq2").set("rot", 9);
    model.component("comp1").geom("geom1").run("sq2");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("pi1", "sq1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("sq2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("carb").prop("Carburization").set("carbmodel", "user");
    model.component("comp1").physics("carb").prop("Carburization").set("cpot", "cenv");
    model.component("comp1").physics("carb").create("cflux1", "CarbonFlux", 1);
    model.component("comp1").physics("carb").feature("carb1").set("D_0", "Dc");
    model.component("comp1").physics("carb").feature("carb1").set("diffusioncoefficient", "user");
    model.component("comp1").physics("carb").feature("carb1").set("D", "Dc");
    model.component("comp1").physics("carb").feature("init1").set("cinit", "c0");
    model.component("comp1").physics("carb").feature("cflux1").selection().set(3, 4, 5, 6, 7, 8);
    model.component("comp1").physics("carb").feature("cflux1").set("masstransfercoefficient", "user");
    model.component("comp1").physics("carb").feature("cflux1").set("b", "kc");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "0.3[mm]");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(3, 4, 5, 6, 7, 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u6e17\u78b3");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,12)");
    model.study("std1").feature("time").setSolveFor("/physics/ht", false);
    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std1").feature("time").setSolveFor("/physics/audc", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/lht1", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ptstr1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"carb.c"});
    model.result("pg1").label("\u78b3\u6d53\u5ea6 (carb)");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("colortablerev", false);
    model.result("pg1").feature("surf1").set("titletype", "manual");
    model.result("pg1").feature("surf1").set("title", "\u78b3\u6d53\u5ea6");
    model.result("pg1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "htc");
    model.func("int1")
         .set("table", new String[][]{{"0", "200"}, {"300", "200"}, {"500", "2800"}, {"650", "750"}, {"1300", "750"}});
    model.func("int1").setIndex("argunit", "degC", 0);
    model.func("int1").setIndex("fununit", "W/(m^2*K)", 0);
    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "Ms");
    model.func("an1").set("expr", "560-470*carb.c");
    model.func("an1").set("args", "carb.c");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").set("fununit", "degC");
    model.func().create("int2", "Interpolation");
    model.func("int2").set("funcname", "EYoung");
    model.func("int2")
         .set("table", new String[][]{{"0", "210"}, {"300", "180"}, {"600", "165"}, {"900", "120"}, {"", ""}});
    model.func("int2").setIndex("argunit", "degC", 0);
    model.func("int2").setIndex("fununit", "GPa", 0);

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "900[degC]");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("ht").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(3, 4, 5, 6, 7, 8);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc(T)");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "80[degC]");
    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "GeneralizedPlaneStrain");
    model.component("comp1").physics("solid").prop("Type2D").set("enable_bending", false);
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("eil", new double[]{0.005, 0, 0, 0, 0.005, 0, 0, 0, 0.005});
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("audc").feature("phase2").active(false);
    model.component("comp1").physics("audc").feature("phase3").active(false);
    model.component("comp1").physics("audc").feature("phase4").active(false);
    model.component("comp1").physics("audc").feature("ptran1").active(false);
    model.component("comp1").physics("audc").feature("ptran2").active(false);
    model.component("comp1").physics("audc").feature("ptran3").active(false);

    model.component("comp1").common().create("minpt1", "CommonInputDef");
    model.component("comp1").common("minpt1").set("quantity", "strainreferencetemperature");
    model.component("comp1").common("minpt1").selection().geom("geom1", 2);
    model.component("comp1").common("minpt1").selection().set(1);
    model.component("comp1").common("minpt1").selection().inherit(false);
    model.component("comp1").common("minpt1").selection().embedded(false);
    model.component("comp1").common("minpt1").selection().extraDim("");
    model.component("comp1").common("minpt1").set("value", "900[degC]");

    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", false);
    model.component("comp1").physics("audc").prop("SolidMechanics").set("plasticity", true);
    model.component("comp1").physics("audc").prop("MaterialProperties").runCommand("makecompoundmaterial");
    model.component("comp1").physics("audc").feature("phase1").runCommand("makephasematerial");
    model.component("comp1").physics("audc").feature("phase1")
         .set("IsotropicHardeningModel", "LinearIsotropicHardening");
    model.component("comp1").physics("audc").feature("phase5").runCommand("makephasematerial");
    model.component("comp1").physics("audc").feature("phase5").set("weightfactorforyieldstress", "geijselaers");
    model.component("comp1").physics("audc").feature("phase5").set("softphase", "phase1");
    model.component("comp1").physics("audc").feature("phase5")
         .set("IsotropicHardeningModel", "LinearIsotropicHardening");
    model.component("comp1").physics("audc").feature("ptran4").set("Ms", "Ms(carb.c)");
    model.component("comp1").physics("audc").feature("ptran4").set("trip", true);
    model.component("comp1").physics("audc").feature("ptran4").set("saturationfunction", "DesalosTRIP");
    model.component("comp1").physics("audc").feature("ptran4").set("recovery", true);

    model.material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup("def").func("int1").set("funcname", "k");
    model.material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "15"}, {"300", "20"}, {"600", "22"}, {"900", "25"}, {"", ""}});
    model.material("mat2").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("def").func("int1").setIndex("fununit", "W/(m*K)", 0);
    model.material("mat2").propertyGroup("def").addInput("temperature");
    model.material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat2").propertyGroup("def").func("int2").set("funcname", "Cp");
    model.material("mat2").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "520"}, {"300", "560"}, {"600", "590"}, {"900", "620"}});
    model.material("mat2").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("def").func("int2").setIndex("fununit", "J/(kg*K)", 0);
    model.material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"7930"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.material("mat2").propertyGroup("ThermalExpansion")
         .set("thermalexpansioncoefficient", new String[]{"2.2e-5"});
    model.material("mat2").propertyGroup("Enu").addInput("temperature");
    model.material("mat2").propertyGroup("Enu").set("E", new String[]{"EYoung(T)"});
    model.material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat2").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "sY");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0", "200"}, {"300", "135"}, {"600", "40"}, {"850", "36"}});
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").setIndex("fununit", "MPa", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").func().create("int2", "Interpolation");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2").set("funcname", "h");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2")
         .set("table", new String[][]{{"0", "1"}, {"300", "15"}, {"600", "11"}, {"900", "0.6"}});
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int2").setIndex("fununit", "GPa", 0);
    model.material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"sY(T)"});
    model.material("mat2").propertyGroup("ElastoplasticModel").set("Et", new String[]{"h(T)"});
    model.material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup("def").func("int1").set("funcname", "k");
    model.material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "44"}, {"300", "38"}, {"600", "30"}, {"900", "24"}});
    model.material("mat3").propertyGroup("def").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("def").func("int1").setIndex("fununit", "W/(m*K)", 0);
    model.material("mat3").propertyGroup("def").addInput("temperature");
    model.material("mat3").propertyGroup("def").func().create("int2", "Interpolation");
    model.material("mat3").propertyGroup("def").func("int2").set("funcname", "Cp");
    model.material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "480"}, {"300", "570"}, {"600", "640"}, {"900", "650"}, {"", ""}});
    model.material("mat3").propertyGroup("def").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("def").func("int2").setIndex("fununit", "J/(kg*K)", 0);
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"7850"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.material("mat3").propertyGroup("ThermalExpansion")
         .set("thermalexpansioncoefficient", new String[]{"1.4e-5"});
    model.material("mat3").propertyGroup("Enu").addInput("temperature");
    model.material("mat3").propertyGroup("Enu").set("E", new String[]{"EYoung(T)"});
    model.material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material("mat3").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat3").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "sY");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0", "1600"}, {"300", "1500"}, {"600", "1400"}, {"900", "100"}, {"", ""}});
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").setIndex("fununit", "MPa", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").func().create("int2", "Interpolation");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2").set("funcname", "h");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2")
         .set("table", new String[][]{{"0", "1"}, {"300", "15"}, {"600", "11"}, {"900", "0.6"}});
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2").setIndex("argunit", "degC", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int2").setIndex("fununit", "GPa", 0);
    model.material("mat3").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"sY(T)"});
    model.material("mat3").propertyGroup("ElastoplasticModel").set("Et", new String[]{"h(T)"});

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/audc", true);
    model.study("std2").feature("time").setSolveFor("/physics/carb", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/lht1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/ptstr1", true);
    model.study("std2").label("\u6dec\u706b");
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "range(0,0.1,10)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", 0.001);
    model.study("std2").feature("time").setSolveFor("/physics/carb", false);
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").feature("time").set("notsolnum", "last");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 101, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"audc.phase1.xi"});
    model.result("pg4").label("\u5965\u6c0f\u4f53 (audc)");
    model.result("pg4").feature("surf1").set("coloring", "gradient");
    model.result("pg4").feature("surf1").set("topcolor", "custom");
    model.result("pg4").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg4").feature("surf1").set("bottomcolor", "custom");
    model.result("pg4").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg4").feature("surf1").set("colortablerev", false);
    model.result("pg4").feature("surf1").set("titletype", "manual");
    model.result("pg4").feature("surf1").set("title", "\u5965\u6c0f\u4f53");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"audc.phase5.xi"});
    model.result("pg5").label("\u9a6c\u6c0f\u4f53 (audc)");
    model.result("pg5").feature("surf1").set("coloring", "gradient");
    model.result("pg5").feature("surf1").set("topcolor", "custom");
    model.result("pg5").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg5").feature("surf1").set("bottomcolor", "custom");
    model.result("pg5").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg5").feature("surf1").set("colortablerev", false);
    model.result("pg5").feature("surf1").set("titletype", "manual");
    model.result("pg5").feature("surf1").set("title", "\u9a6c\u6c0f\u4f53");
    model.result("pg2").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().dataset("mir1").set("method", "pointdir");
    model.result().dataset("mir1").set("pddir", new int[]{1, 0});
    model.result().dataset().create("sec1", "Sector2D");
    model.result().dataset("sec1").set("data", "mir1");
    model.result().dataset("sec1").set("sectors", 20);
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature().move("surf2", 0);
    model.result("pg1").feature("surf2").set("data", "sec1");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").set("data", "mir1");
    model.result("pg6").label("\u9a6c\u6c0f\u4f53\u5f00\u59cb\u6e29\u5ea6");
    model.result("pg6").set("edges", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "sec1");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").feature("surf1").set("titletype", "none");
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("expr", "Ms(carb.c)");
    model.result("pg6").feature("surf2").set("unit", "degC");
    model.result("pg6").feature("surf2").set("descractive", true);
    model.result("pg6").feature("surf2").set("descr", "\u9a6c\u6c0f\u4f53\u5f00\u59cb\u6e29\u5ea6");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u6b8b\u4f59\u5e94\u529b");
    model.result("pg7").set("data", "mir1");
    model.result("pg7").set("edges", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("data", "sec1");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").set("titletype", "none");
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "solid.sp2Gp");
    model.result("pg7").run();

    model.title("\u94a2\u9f7f\u8f6e\u7684\u6e17\u78b3\u6dec\u706b");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u94a2\u9f7f\u8f6e\u7684\u6e17\u78b3\u6dec\u706b\u5de5\u827a\u3002\u78b3\u5728\u9f7f\u8f6e\u8868\u9762\u7684\u6269\u6563\u4f1a\u5f71\u54cd\u9a6c\u6c0f\u4f53\u76f8\u53d8\u7684\u5f00\u59cb\u3002\u672c\u4f8b\u8ba1\u7b97\u9f7f\u8f6e\u7684\u6b8b\u4f59\u5e94\u529b\uff0c\u7ed3\u679c\u8868\u660e\uff0c\u9f7f\u8f6e\u6839\u90e8\u5b58\u5728\u8f83\u5927\u7684\u6b8b\u4f59\u538b\u5e94\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("carburization_and_quenching_of_a_steel_gear.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
