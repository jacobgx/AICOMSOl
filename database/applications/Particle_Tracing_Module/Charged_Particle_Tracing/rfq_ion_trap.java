/*
 * rfq_ion_trap.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:52 by COMSOL 6.3.0.290. */
public class rfq_ion_trap {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

    model.study().create("std1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_rod", "8[mm]", "\u6746\u534a\u5f84");
    model.param().set("L_rod", "250[mm]", "\u6746\u957f\u5ea6");
    model.param().set("r0", "7[mm]", "\u56db\u6781\u6746\u5185\u534a\u5f84");
    model.param().set("r_in", "1[mm]", "\u5165\u53e3\u534a\u5f84");
    model.param().set("r_case", "2*(r_rod+r0)", "\u7bb1\u4f53\u534a\u5f84");
    model.param().set("V_AC", "125[V]", "\u4ea4\u6d41\u7535\u538b");
    model.param().set("f", "500[kHz]", "\u4ea4\u6d41\u7535\u9891\u7387");
    model.param().set("omega", "2*pi*f", "\u89d2\u9891\u7387");
    model.param().set("T_osc", "1/f", "\u65f6\u95f4\u5468\u671f");
    model.param().set("mass_ion", "133[amu]", "\u79bb\u5b50\u8d28\u91cf");
    model.param().set("q_param", "4*e_const*Vac/mass_ion/(omega*r0)^2", "\u9a6c\u4fee\u7cfb\u6570 (q)");
    model.param().set("Pgas", "6[Pa]", "\u7f13\u51b2\u6c14\u538b");
    model.param().set("Tgas", "273[K]", "\u7f13\u51b2\u6c14\u6e29\u5ea6");
    model.param().set("nd", "Pgas/Tgas/k_B_const", "\u7f13\u51b2\u6c14\u6570\u5bc6\u5ea6");
    model.param().set("E_in", "60[eV]", "\u521d\u59cb\u52a8\u80fd");
    model.param().set("V1", "-10[V]", "\u6700\u5c0f\u6355\u83b7\u7535\u538b");
    model.param().set("V2", "10[V]", "\u6700\u5927\u6355\u83b7\u7535\u538b");
    model.param().set("x_min", "L_rod/10*9", "\u6355\u83b7\u7535\u4f4d\u6700\u4f4e\u4f4d\u7f6e");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("funcname", "trap_pot");
    model.component("comp1").func("pw1").set("smooth", "contd2");
    model.component("comp1").func("pw1").set("smoothzone", "L_rod/4");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x_min", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "V1/x_min*x", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x_min", 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "L_rod", 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "V1+((V2-V1)/(L_rod-x_min))*(x-x_min)", 1, 2);
    model.component("comp1").func("pw1").set("argunit", "m");
    model.component("comp1").func("pw1").set("fununit", "V");
    model.component("comp1").func("pw1").createPlot("pg1");

    model.result("pg1").run();

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "ext_pot");
    model.component("comp1").func("an1").set("expr", "V1/x_min*x");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").setIndex("plotargs", "L_rod", 0, 2);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("xdataunit", "mm");
    model.result("pg1").feature("plot1").set("linestyle", "cycle");
    model.result("pg1").feature("plot1").set("linewidth", 3);
    model.result("pg1").feature("plot1").set("legend", true);
    model.result("pg1").run();
    model.result().dataset("pw1_ds1").set("function", "all");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("plot2", "plot1");
    model.result("pg1").run();
    model.result("pg1").feature("plot2").set("expr", "comp1.ext_pot(x)");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Perfect vacuum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "0[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});

    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_rod");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"r_rod+r0", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"-(r_rod+r0)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c3", "c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3")
         .set("pos", new String[]{"0", "-(r_rod+r0)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c4", "c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4")
         .set("pos", new String[]{"0", "r_rod+r0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c4");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L_rod", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_case");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L_rod");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("ext1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 18, 19, 20, 21);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").add(4, 5, 6, 7, 18, 19, 20, 21);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection("sel3").add(8, 9, 10, 11, 13, 14, 15, 16);

    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").set("V0", "trap_pot(x)");
    model.component("comp1").physics("es").feature("pot1").selection().named("sel1");
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").selection().named("sel3");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "V_AC");
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot2").selection().named("sel2");
    model.component("comp1").physics("ec").feature("pot2").set("V0", "-V_AC");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "f");
    model.study("std1").feature("freq").setSolveFor("/physics/es", false);
    model.study("std1").feature("freq").set("usesol", true);
    model.study("std1").feature("freq").set("notsolmethod", "sol");
    model.study("std1").feature("freq").set("notstudy", "std1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u52bf (ec)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "V2");
    model.result("pg4").feature("vol1").set("colortable", "Dipole");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u573a (ec)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "ec.normE");
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg5").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg5").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg5").feature("mslc1").set("colortable", "Prism");
    model.result("pg5").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg5").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg5").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.02);
    model.result("pg5").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg5").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("data", "parent");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg5").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").run();

    model.component("comp1").physics("cpt").prop("StoreParticleStatusData")
         .setIndex("StoreParticleStatusData", 1, 0);
    model.component("comp1").physics("cpt").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("cpt").feature("pp1").set("mp", "mass_ion");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").create("pbeam1", "ParticleBeam", 2);
    model.component("comp1").physics("cpt").feature("pbeam1").selection().set(1);
    model.component("comp1").physics("cpt").feature("pbeam1").setIndex("rt", "range(0,(5*T_osc-0)/99,5*T_osc)", 0);
    model.component("comp1").physics("cpt").feature("pbeam1").setIndex("N", 20, 0);
    model.component("comp1").physics("cpt").feature("pbeam1")
         .set("TransverseVelocityDistributionSpecification", "SpecifyPhaseSpaceEllipseDimensions");
    model.component("comp1").physics("cpt").feature("pbeam1").set("xm", "r_in");
    model.component("comp1").physics("cpt").feature("pbeam1").set("El", "E_in");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef1").selection().set(1);
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").create("ef2", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef2").selection().set(1);
    model.component("comp1").physics("cpt").feature("ef2").set("E_src", "root.comp1.ec.Ex");
    model.component("comp1").physics("cpt").feature("ef2").set("TimeDependenceOfField", "TimeHarmonic");
    model.component("comp1").physics("cpt").create("col1", "Collisions", 3);
    model.component("comp1").physics("cpt").feature("col1").selection().set(1);
    model.component("comp1").physics("cpt").feature("col1").set("Nd", "nd");
    model.component("comp1").physics("cpt").feature("col1").set("mg", "2[amu]*N_A_const");
    model.component("comp1").physics("cpt").feature("col1").set("T", "Tgas");
    model.component("comp1").physics("cpt").feature("col1").create("ela1", "Elastic", 3);
    model.component("comp1").physics("cpt").create("out1", "Outlet", 2);
    model.component("comp1").physics("cpt").feature("out1").selection().set(22);

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/es", false);
    model.study("std2").feature("time").setSolveFor("/physics/ec", false);
    model.study("std2").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std2").feature("time").set("tunit", "\u00b5s");
    model.study("std2").feature("time").set("tlist", "range(0,5.0,300)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol3").feature("t1").set("timestepgenalpha", "1e-8");

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "part1");
    model.result("pg6").setIndex("looplevel", 61, 0);
    model.result("pg6").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg6").create("traj1", "ParticleTrajectories");
    model.result("pg6").feature("traj1").set("pointtype", "point");
    model.result("pg6").feature("traj1").set("linetype", "none");
    model.result("pg6").feature("traj1").create("col1", "Color");
    model.result("pg6").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "part1");
    model.result("pg7").setIndex("looplevel", 61, 0);
    model.result("pg7").label("\u5e73\u5747\u675f\u6d41\u4f4d\u7f6e (cpt)");
    model.result("pg7").create("pttraj1", "PointTrajectories");
    model.result("pg7").feature("pttraj1").set("plotdata", "global");
    model.result("pg7").feature("pttraj1").set("globalexpr", new String[]{"cpt.qavx", "cpt.qavy", "cpt.qavz"});
    model.result("pg7").feature("pttraj1").create("col1", "Color");
    model.result("pg7").feature("pttraj1").feature("col1").set("expr", "cpt.e1hrms");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("traj1").set("sphereradiusscaleactive", true);
    model.result("pg6").feature("traj1").set("sphereradiusscale", 0.25);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("traj1").feature("col1").set("expr", "cpt.Ep");
    model.result("pg6").feature("traj1").feature("col1").set("unit", "eV");
    model.result("pg6").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("data", "part1");
    model.result("pg1").feature("glob1").setIndex("expr", "-t/300[us]*12[V]", 0);
    model.result("pg1").feature("glob1").setIndex("unit", "us", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "Scaled time", 0);
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "cpt.ave(qx)");
    model.result("pg1").feature("glob1").set("xdataunit", "mm");
    model.result("pg1").feature("glob1").set("linewidth", 3);
    model.result("pg1").feature("glob1").set("legend", false);
    model.result("pg1").run();
    model.result("pg1").feature("glob1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").feature("col1").set("expr", "cpt.Epave");
    model.result("pg1").feature("glob1").feature("col1").set("unit", "eV");
    model.result("pg1").feature("glob1").feature("col1").set("colortable", "Thermal");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 250);
    model.result("pg1").set("ymin", -12);
    model.result("pg1").set("ymax", 10);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "part1");
    model.result("pg8").setIndex("looplevelinput", "interp", 0);
    model.result("pg8").setIndex("interp", 20, 0);
    model.result("pg8").create("ptp1", "Particle1D");
    model.result("pg8").feature("ptp1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptp1").set("linewidth", "preference");
    model.result("pg8").feature("ptp1").set("expr", "qz");
    model.result("pg8").feature("ptp1").set("unit", "mm");
    model.result("pg8").feature("ptp1").set("xdata", "expr");
    model.result("pg8").feature("ptp1").set("xdataexpr", "qy");
    model.result("pg8").feature("ptp1").set("xdataunit", "mm");
    model.result("pg8").feature("ptp1").set("linestyle", "none");
    model.result("pg8").feature("ptp1").set("linemarker", "circle");
    model.result("pg8").run();
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", -5);
    model.result("pg8").set("xmax", 5);
    model.result("pg8").set("ymin", -5);
    model.result("pg8").set("ymax", 5);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").run();
    model.result("pg8").setIndex("interp", 60, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("interp", 120, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("interp", 180, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("interp", 240, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("interp", 300, 0);
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").setIndex("interp", 20, 0);
    model.result("pg9").run();
    model.result("pg9").feature("ptp1").set("expr", "cpt.vy");
    model.result("pg9").feature("ptp1").set("unit", "km/s");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("interp", 60, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("interp", 120, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("interp", 180, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("interp", 240, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("interp", 300, 0);
    model.result("pg9").run();

    model.component("comp1").physics("es").feature("pot1").set("V0", "ext_pot(x)");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg2").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/es", false);
    model.study("std3").feature("time").setSolveFor("/physics/ec", false);
    model.study("std3").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std3").feature("time").set("tunit", "\u00b5s");
    model.study("std3").feature("time").set("tlist", "range(300,10,400)");
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initmethod", "sol");
    model.study("std3").feature("time").set("initstudy", "std2");
    model.study("std3").feature("time").set("solnum", "last");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std1");
    model.study("std3").showAutoSequences("all");

    model.sol("sol4").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol4").feature("t1").set("timestepgenalpha", "1e-8");

    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("part2", "Particle");
    model.result().dataset("part2").set("solution", "sol4");
    model.result().dataset("part2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part2").set("geom", "geom1");
    model.result().dataset("part2").set("pgeom", "pgeom_cpt");
    model.result().dataset("part2").set("pgeomspec", "fromphysics");
    model.result().dataset("part2").set("physicsinterface", "cpt");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "part2");
    model.result("pg10").setIndex("looplevel", 11, 0);
    model.result("pg10").label("\u7c92\u5b50\u8f68\u8ff9 (cpt) 1");
    model.result("pg10").create("traj1", "ParticleTrajectories");
    model.result("pg10").feature("traj1").set("pointtype", "point");
    model.result("pg10").feature("traj1").set("linetype", "none");
    model.result("pg10").feature("traj1").create("col1", "Color");
    model.result("pg10").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "part2");
    model.result("pg11").setIndex("looplevel", 11, 0);
    model.result("pg11").label("\u5e73\u5747\u675f\u6d41\u4f4d\u7f6e (cpt) 1");
    model.result("pg11").create("pttraj1", "PointTrajectories");
    model.result("pg11").feature("pttraj1").set("plotdata", "global");
    model.result("pg11").feature("pttraj1").set("globalexpr", new String[]{"cpt.qavx", "cpt.qavy", "cpt.qavz"});
    model.result("pg11").feature("pttraj1").create("col1", "Color");
    model.result("pg11").feature("pttraj1").feature("col1").set("expr", "cpt.e1hrms");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("traj1").set("linetype", "line");
    model.result("pg10").feature("traj1").set("sphereradiusscaleactive", true);
    model.result("pg10").feature("traj1").set("sphereradiusscale", 0.25);
    model.result("pg10").run();
    model.result("pg10").feature("traj1").feature("col1").set("expr", "cpt.Ep");
    model.result("pg10").feature("traj1").feature("col1").set("unit", "eV");
    model.result("pg10").run();
    model.result().export("anim1").showFrame();
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg10");
    model.result().export("anim2").run();

    model.title("\u5c04\u9891\u56db\u6781\u79bb\u5b50\u9631");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u56db\u6781\u64cd\u4f5c\uff0c\u4f7f\u7528\u7f13\u51b2\u6c14\u4f53\u51b7\u5374\u65b9\u6cd5\u6765\u51b7\u5374\u79bb\u5b50\u675f\uff0c\u5e76\u901a\u8fc7\u9677\u9631\u52bf\u6355\u83b7\u79bb\u5b50\u3002\u51b7\u5374\u7684\u79bb\u5b50\u88ab\u6355\u83b7\u540e\uff0c\u4f1a\u805a\u96c6\u6210\u7a7a\u95f4\u4e0a\u5c40\u57df\u5316\u7684\u56e2\u7c07\uff0c\u968f\u540e\u53ef\u4ee5\u901a\u8fc7\u6539\u53d8\u76f4\u6d41\u7535\u538b\u6765\u91ca\u653e\u8fd9\u4e9b\u79bb\u5b50\u3002\u8fd9\u6837\uff0c\u4fbf\u80fd\u5c06\u8fde\u7eed\u7684\u79bb\u5b50\u675f\u8f6c\u6362\u4e3a\u8109\u51b2\u79bb\u5b50\u675f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("rfq_ion_trap.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
