/*
 * inductor_coil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:00 by COMSOL 6.3.0.290. */
public class inductor_coil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials_with_Deforming_Geometries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties")
         .set("ChargeTransportModel", "SupportingElectrolyte");
    model.component("comp1").physics("tcd").field("concentration").field("c");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"c"});

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 2);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 2);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("desdg1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/desdg1", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/desdg1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r0", "20e-6[m]", "\u87ba\u65cb\u8d77\u59cb\u534a\u5f84");
    model.param().set("a1", "10e-6[m]", "\u7ebf\u5708\u5bbd\u5ea6");
    model.param().set("a_tot", "a1*2", "\u87ba\u65cb\u4e00\u5708\u7684\u5bbd\u5ea6");
    model.param().set("laps", "3", "\u87ba\u65cb\u5708\u6570");
    model.param().set("w_tot", "2*pi*laps", "\u603b\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("c_ref", "500[mol/m^3]", "\u7535\u89e3\u8d28\u53c2\u8003\u6d53\u5ea6");
    model.param().set("D_Cu", "4.5e-10", "\u6269\u6563\u7cfb\u6570");
    model.param().set("d_dl", "50e-6[m]", "\u6269\u6563\u5c42\u539a\u5ea6");
    model.param().set("Eeq_Cu", "0.34[V]", "Cu/Cu \u5e73\u8861\u7535\u4f4d");
    model.param().set("d_pr", "10e-6[m]", "\u5149\u523b\u80f6\u5c42\u539a\u5ea6");
    model.param().set("i_avg", "-10[A/dm^2]", "\u5e73\u5747\u9634\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0", "100[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("alpha_a", "1.5", "\u9633\u6781\u4f20\u9012\u7cfb\u6570");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "spiralX");
    model.func("an1").set("expr", "-(s/(2*pi)*a_tot+R)*sin(s)");
    model.func("an1").set("args", "s, R");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").setIndex("argunit", "m", 1);
    model.func("an1").set("fununit", "m");
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "spiralY");
    model.func("an2").set("expr", "-(s/(2*pi)*a_tot+R)*cos(s)");
    model.func("an2").set("args", "s, R");
    model.func("an2").setIndex("argunit", 1, 0);
    model.func("an2").setIndex("argunit", "m", 1);
    model.func("an2").set("fununit", "m");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").set("parmax", "w_tot");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .set("coord", new String[]{"spiralX(s,r0)", ""});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .setIndex("coord", "spiralY(s,r0)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pc2", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc2").set("parmax", "w_tot");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc2")
         .set("coord", new String[]{"spiralX(s,r0+a1)", ""});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc2")
         .setIndex("coord", "spiralY(s,r0+a1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"a1", "2*a1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "-r0-a1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "2*a1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1")
         .set("pos", new String[]{"-a1/2", "-a1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "a1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"a1", "2*a1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"-a1", "-r0-2*a1-laps*a_tot"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", "2*a1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2")
         .set("pos", new String[]{"-1.5*a1", "-r0-4*a1-laps*a_tot"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("fil1", "pc1", "pc2", "r2", "sq1", "sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "d_pr", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("size", new String[]{"2*(r0+a_tot*laps+d_dl)", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "2*(r0+a_tot*laps+d_dl)+3*a1", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "d_dl", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "-2*a1", "d_pr+d_dl/2"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").label("\u56fa\u5b9a\u57df");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("com1").label("\u53d8\u5f62\u57df");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection("sel2").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(4);
    model.component("comp1").selection("sel3").label("\u672c\u4f53\u7535\u89e3\u8d28\u8fb9\u754c");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(8, 13, 18, 25, 30);
    model.component("comp1").selection("sel4").label("\u9634\u6781");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("adj1").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel4", "adj1"});
    model.component("comp1").selection("dif1").label("\u5149\u523b\u80f6\u5782\u76f4\u58c1");

    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("ChargeTransportModel", "Electroanalysis");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c", new String[]{"D_Cu", "0", "0", "0", "D_Cu", "0", "0", "0", "D_Cu"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_ref", 0);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("tcd").feature("es1").selection().named("sel4");
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "AverageCurrentDensity");
    model.component("comp1").physics("tcd").feature("es1").set("Ial", "i_avg");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Eeq_Cu");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "alpha_a");
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 2);
    model.component("comp1").physics("tcd").feature("conc1").selection().named("sel3");
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_ref", 0);
    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_concentration", 1);
    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_electricpotential", 1);

    model.component("comp1").common().create("pres1", "PrescribedDeformationDeformedGeometry");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").selection().named("sel1");

    model.component("comp1").multiphysics("ndbdg1").set("BoundaryCondition", "ZeroNormalDisplacement");
    model.component("comp1").multiphysics().create("ndbdg2", "NonDeformingBoundaryDeformedGeometry", 2);
    model.component("comp1").multiphysics("ndbdg2").selection().named("dif1");
    model.component("comp1").multiphysics("ndbdg2").set("BoundaryCondition", "ZeroNormalDisplacement");
    model.component("comp1").multiphysics("ndbdg2").set("LineDeformation", true);
    model.component("comp1").multiphysics("ndbdg2").set("l", new int[]{0, 0, 1});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().clear();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(25);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "a1/2");
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(8, 13, 18, 30);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "a1/2");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run("ftet1");

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,30,180)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg1").set("edges", false);
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "c");
    model.result("pg1").feature("slc1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "c");
    model.result("pg1").feature("surf1").set("inheritplot", "slc1");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("sel4");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.tflux_cx", "tcd.tflux_cy", "tcd.tflux_cz"});
    model.result("pg1").feature("str1")
         .set("descr", "\u603b\u901a\u91cf \uff08\u7a7a\u95f4\u548c\u6750\u6599\u5750\u6807\u7cfb\uff09");
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("startmethod", "coord");
    model.result("pg1").feature("str1").set("xcoord", 0);
    model.result("pg1").feature("str1").set("ycoord", "range(-150e-6,20e-6,150e-6)");
    model.result("pg1").feature("str1").set("zcoord", "d_dl+d_pr");
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("color", "black");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().named("sel4");
    model.result("pg2").set("frametype", "geometry");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "tcd.iloc_er1");
    model.result("pg2").feature("surf1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "tcd.sbtot");
    model.result("pg3").feature("surf1").set("descr", "\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316");
    model.result("pg3").feature("surf1").set("unit", "\u00b5m");
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "dset1");
    model.result("pg3").feature("surf2").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "black");
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection().named("sel4");
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u89e3\u8d28\u4e2d\u7684\u94dc\u6d53\u5ea6");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6781\u53cd\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").run();
    model.result("pg3").label("\u6c89\u79ef\u539a\u5ea6");

    model.title("\u7535\u611f\u7ebf\u5708\u7684\u7535\u9540");

    model
         .description("\u672c\u4f8b\u5bf9\u4e00\u4e2a\u4e09\u7ef4\u7535\u611f\u7ebf\u5708\u7684\u7535\u9540\u8fc7\u7a0b\u5efa\u6a21\u3002\n\n\u51e0\u4f55\u5305\u542b\u62c9\u4f38\u7535\u9540\u56fe\u6848\u5f97\u5230\u7684\u7edd\u7f18\u63a9\u819c\uff0c\u4ee5\u53ca\u5149\u523b\u80f6\u4e0a\u65b9\u7684\u6269\u6563\u5c42\u3002\u7535\u89e3\u6db2\u4e2d\u94dc\u79bb\u5b50\u7684\u8d28\u91cf\u4f20\u9012\u662f\u5f71\u54cd\u7535\u9540\u52a8\u529b\u5b66\u7684\u4e3b\u8981\u56e0\u7d20\uff0c\u5bfc\u81f4\u5728\u7535\u9540\u56fe\u6848\u7684\u5916\u4fa7\u51fa\u73b0\u8f83\u5927\u7684\u6c89\u79ef\u901f\u7387\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u53d8\u5f62\u51e0\u4f55\u201d\u63a5\u53e3\u8fdb\u884c\u77ac\u6001\u7814\u7a76\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("inductor_coil.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
