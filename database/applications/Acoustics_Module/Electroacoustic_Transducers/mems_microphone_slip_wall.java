/*
 * mems_microphone_slip_wall.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class mems_microphone_slip_wall {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ta", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cir", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("dia_t", "0.5[um]", "\u819c\u7247\u539a\u5ea6");
    model.param().set("back_t", "3[um]", "MPP \u7684\u539a\u5ea6");
    model.param().set("dia_r", "200[um]", "\u632f\u819c\u76f4\u5f84");
    model.param().set("gap", "2[um]", "\u632f\u819c\u4e0e MPP \u4e4b\u95f4\u7684\u95f4\u9699");
    model.param().set("hole_r", "3[um]", "\u5b54\u534a\u5f84");
    model.param().set("vent_d", "0.9*dia_r", "\u901a\u98ce\u5b54\u7684\u4f4d\u7f6e");
    model.param().set("vent_r", "7[um]", "\u901a\u6c14\u5b54\u534a\u5f84");
    model.param().set("hole_dist", "9*hole_r", "\u5b54\u95f4\u8ddd");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "dia_r*1");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "200[\u00b5m]");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-100[\u00b5m]"});
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "dia_t");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{0, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl2").label("\u632f\u819c");
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl2");
    model.component("comp1").geom("geom1").feature("cyl3").label("\u80cc\u677f");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "back_t");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "gap+dia_t"});
    model.component("comp1").geom("geom1").feature().duplicate("cyl4", "cyl3");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "hole_r");
    model.component("comp1").geom("geom1").feature("cyl4").label("\u521d\u59cb\u5b54");
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "85[\u00b5m]", 0);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "35[\u00b5m]", 1);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("arr1", "Array");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl4");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", 2);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"hole_dist", "0", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("arr1");
    model.component("comp1").geom("geom1").feature("arr2").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr2").set("linearsize", 2);
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"hole_dist/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr2").setIndex("displ", "sin(pi/3)*hole_dist", 1);
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("arr2");
    model.component("comp1").geom("geom1").feature("arr3").set("fullsize", new int[]{8, 8, 1});
    model.component("comp1").geom("geom1").feature("arr3").set("displ", new String[]{"2*hole_dist", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr3").setIndex("displ", "2*sin(pi/3)*hole_dist", 1);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5b54");
    model.component("comp1").geom("geom1").feature("arr3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("cylsel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("cylsel1").set("input", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "4*dia_r");
    model.component("comp1").geom("geom1").feature("cylsel1").set("rin", "0.75*dia_r");
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "somevertex");
    model.component("comp1").geom("geom1").feature("cylsel1").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5b54 tmp");
    model.component("comp1").geom("geom1").feature("cylsel1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("cylsel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("cyl3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cyl4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("arr1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("arr2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("arr3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cylsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u5b54");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("cyl5", false);
    model.component("comp1").geom("geom1").feature("cyl5").label("\u901a\u98ce\u53e3 1");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "vent_r");
    model.component("comp1").geom("geom1").feature("cyl5").set("h", "back_t");
    model.component("comp1").geom("geom1").feature("cyl5")
         .set("pos", new String[]{"cos(5*pi/180)*vent_d", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl5").setIndex("pos", "sin(5*pi/180)*vent_d", 1);
    model.component("comp1").geom("geom1").feature("cyl5").setIndex("pos", "gap+dia_t", 2);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u901a\u98ce\u53e3");
    model.component("comp1").geom("geom1").feature("cyl5").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").feature().duplicate("cyl6", "cyl5");
    model.component("comp1").geom("geom1").feature("cyl6").label("\u901a\u98ce\u53e3 2");
    model.component("comp1").geom("geom1").feature("cyl6").setIndex("pos", "cos(15*pi/180)*vent_d", 0);
    model.component("comp1").geom("geom1").feature("cyl6").setIndex("pos", "sin(15*pi/180)*vent_d", 1);
    model.component("comp1").geom("geom1").feature().duplicate("cyl7", "cyl6");
    model.component("comp1").geom("geom1").feature("cyl7").label("\u901a\u98ce\u53e3 3");
    model.component("comp1").geom("geom1").feature("cyl7").setIndex("pos", "cos(25*pi/180)*vent_d", 0);
    model.component("comp1").geom("geom1").feature("cyl7").setIndex("pos", "sin(25*pi/180)*vent_d", 1);
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter("cyl3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("cyl5");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("cyl6");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("cyl7");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("\u901a\u98ce\u53e3");
    model.component("comp1").geom("geom1").run("cyl7");
    model.component("comp1").geom("geom1").create("cylsel2", "CylinderSelection");
    model.component("comp1").geom("geom1").nodeGroup("grp2").remove("cylsel2", false);
    model.component("comp1").geom("geom1").feature("cylsel2").set("r", "1[mm]");
    model.component("comp1").geom("geom1").feature("cylsel2").set("entitydim", -1);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u6240\u6709\u5706\u67f1");
    model.component("comp1").geom("geom1").feature("cylsel2").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("cylsel2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("csel4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-100[\u00b5m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "dia_r");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 30);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.5[mm]", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("ext1", "uni1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u632f\u819c");
    model.component("comp1").selection("sel1").set(3);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u632f\u819c\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6ed1\u79fb\u58c1\u8fb9\u754c");
    model.component("comp1").selection("sel2").set(4, 8);
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(4, 8);
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior", "interior"});
    model.component("comp1").selection("sel2").set(4, 8);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8f83\u5927\u7684\u7a7a\u6c14\u57df");
    model.component("comp1").selection("sel3").set(1, 2, 4, 6, 7);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7a7a\u6c14 - TA");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"sel3", "geom1_csel1_dom", "geom1_csel3_dom"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(13, 14);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection("sel4").label("\u5bf9\u79f0");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel5").set();
    model.component("comp1").selection("sel5").all();
    model.component("comp1").selection("sel5").label("\u6240\u6709\u57df - \u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("MPP \u9876\u90e8");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(15);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u7a7a\u6c14 - ACPR");
    model.component("comp1").selection("sel7").set(1);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u80cc\u677f\u8fb9\u754c");
    model.component("comp1").selection("sel8").set(8);
    model.component("comp1").selection("sel8").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel8").set(8);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u9759\u7535\u57df");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel1", "uni1"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").label("Silicon");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.7);
    model.component("comp1").material("mat2").set("roughness", 0.5);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material("mat2").selection().named("sel1");

    model.component("comp1").common("free1").selection().set(2, 4);
    model.component("comp1").common("free1").set("smoothingType", "laplace");
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().named("sel4");

    model.component("comp1").physics("ta").selection().named("uni1");
    model.component("comp1").physics("ta").create("slw1", "SlipWall", 2);
    model.component("comp1").physics("ta").feature("wall1").set("MechanicalCondition", "Slip");
    model.component("comp1").physics("ta").feature("wall1").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("ta").feature("slw1").label("\u6ed1\u79fb\u58c1 - MPP");
    model.component("comp1").physics("ta").feature("slw1").selection().named("sel2");
    model.component("comp1").physics("ta").create("slw2", "SlipWall", 2);
    model.component("comp1").physics("ta").feature("slw2").label("\u6ed1\u79fb\u58c1 - \u632f\u819c");
    model.component("comp1").physics("ta").feature("slw2").selection().named("adj1");
    model.component("comp1").physics("ta").feature("slw2").set("MechanicalCondition", "MovingWall");
    model.component("comp1").physics("ta").feature("slw2").set("u_wall_src", "root.comp1.solid.us_tx");
    model.component("comp1").physics("ta").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ta").feature("sym1").selection().named("sel4");
    model.component("comp1").physics("ta").create("pra1", "PressureAdiabatic", 2);
    model.component("comp1").physics("ta").feature("pra1").selection().set(22);
    model.component("comp1").physics("ta").feature("pra1").set("pbnd", "linper(1[Pa])");
    model.component("comp1").physics("ta").create("wall2", "Wall", 2);
    model.component("comp1").physics("ta").feature("wall2").selection().set(3, 123, 124);
    model.component("comp1").physics("solid").selection().named("sel1");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel5");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel4");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(12);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "ForceDefArea");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceDeformedArea_src", "root.comp1.ta.slw2.TractionPerAreax");
    model.component("comp1").physics("es").selection().named("uni2");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 3);
    model.component("comp1").physics("es").feature("ccns1").selection().named("sel1");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(9);
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("sel8");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics("es").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("es").feature("symp1").selection().named("sel4");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.es.V0_1");
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 1, 1, 0);
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "10[Gohm]");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 3);
    model.component("comp1").multiphysics().create("tsb1", "ThermoacousticStructureBoundary", 2);
    model.component("comp1").multiphysics("tsb1").selection().set(9);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "hole_r/1.5");
    model.component("comp1").mesh("mesh1").feature("size1").label("\u5927\u5c0f - \u5b54");
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").label("\u5927\u5c0f - \u901a\u98ce\u53e3");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_csel3_dom");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "vent_r/1.5");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("sel6");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").label("\u626b\u63a0 - \u95f4\u9699\u548c\u632f\u819c");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "number");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").label("\u626b\u63a0 - \u5b54");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature().duplicate("swe3", "swe2");
    model.component("comp1").mesh("mesh1").feature("swe3").label("\u626b\u63a0 - \u901a\u98ce\u53e3");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().named("geom1_csel3_dom");
    model.component("comp1").mesh("mesh1").run("swe3");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2, 6);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe4").selection().set(1, 7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("plist", "10^{range(log10(200),1/4,log10(20000))}");
    model.study("std1").feature("frlin").set("usestol", true);
    model.study("std1").feature("frlin").set("stol", "1e-3");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("colortable", "Wave");
    model.result("pg1").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u58f0\u901f (ta)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg2").feature("slc1").set("evalmethodactive", "off");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("evalmethodactive", "off");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("evalmethodactive", "off");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("evalmethodactive", "off");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("evalmethodactive", "off");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6\u53d8\u5316 (ta)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("expr", "ta.T_t");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("colortable", "ThermalWave");
    model.result("pg3").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("mslc1").set("smooth", "internal");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result().dataset("dset1").set("frametype", "spatial");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 9, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").label("\u4f4d\u79fb (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "SpectrumLight");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u52bf (es)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "V");
    model.result("pg5").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg5").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg5").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg5").feature("mslc1").set("colortable", "Dipole");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg5").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg5").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg5").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.02);
    model.result("pg5").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg5").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("data", "parent");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg5").feature("strmsl1").feature("col1").set("evalmethodactive", "off");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg5").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (es)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("expr", "es.normE");
    model.result("pg6").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg6").feature("mslc1").set("colortable", "Prism");
    model.result("pg6").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg6").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg6").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg6").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg6").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg6").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg6").feature("strmsl1").set("titletype", "none");
    model.result("pg6").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg6").feature("strmsl1").set("udist", 0.02);
    model.result("pg6").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg6").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("inheritcolor", false);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("data", "parent");
    model.result("pg6").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg6").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg6").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg6").feature("strmsl1").feature("col1").set("evalmethodactive", "off");
    model.result("pg6").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg6").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 9, 0);
    model.result("pg7").label("\u52a8\u7f51\u683c");
    model.result("pg7").create("mesh1", "Mesh");
    model.result("pg7").feature("mesh1").set("meshdomain", "volume");
    model.result("pg7").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg7").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg7").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg7").feature("mesh1").feature("sel1").selection().set(2, 3, 4);
    model.result("pg7").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg7").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg7").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("sectors", 12);
    model.result().dataset("sec1").set("include", "manual");
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result().dataset().duplicate("sec2", "sec1");
    model.result().dataset("sec2").set("sectorsinclude", 9);
    model.result().dataset("sec2").set("startsector", -2);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u9891\u7387\u54cd\u5e94");
    model.result("pg8").create("oct1", "OctaveBand");
    model.result("pg8").feature("oct1").set("quantity", "bandpower");
    model.result("pg8").feature("oct1").set("markerpos", "datapoints");
    model.result("pg8").feature("oct1").set("linewidth", "preference");
    model.result("pg8").feature("oct1").selection().geom("geom1");
    model.result("pg8").feature("oct1").set("expr", "es.V0_1");
    model.result("pg8").feature("oct1").set("amplref", "1/sqrt(2)");
    model.result("pg8").feature("oct1").set("quantity", "continuous");
    model.result("pg8").feature("oct1").set("linemarker", "circle");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u58f0\u538b");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("TA");
    model.result("pg9").feature("surf1").set("colortable", "Wave");
    model.result("pg9").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u58f0\u901f");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "ta.v_rms");
    model.result("pg10").feature("surf1").set("unit", "mm/s");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u7f29\u7565\u56fe");
    model.result("pg11").set("data", "sec1");
    model.result("pg11").set("edges", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("\u5706\u67f1");
    model.result("pg11").feature("surf1").set("expr", "1");
    model.result("pg11").run();
    model.result("pg11").feature("surf1").create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().set(3, 123, 124, 125, 127);
    model.result("pg11").run();
    model.result("pg11").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg11").run();
    model.result("pg11").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg11").feature("surf1").feature("mtrl1").set("color", "black");
    model.result("pg11").run();
    model.result("pg11").create("surf2", "Surface");
    model.result("pg11").feature("surf2").label("MPP");
    model.result("pg11").feature("surf2").set("data", "sec2");
    model.result("pg11").feature("surf2").set("expr", "1");
    model.result("pg11").feature("surf2").create("sel1", "Selection");
    model.result("pg11").feature("surf2").feature("sel1").selection().named("sel8");
    model.result("pg11").run();
    model.result("pg11").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg11").run();
    model.result("pg11").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg11").feature("surf2").feature("mtrl1").set("color", "gray");
    model.result("pg11").run();
    model.result("pg11").create("surf3", "Surface");
    model.result("pg11").feature("surf3").label("\u4f4d\u79fb");
    model.result("pg11").feature("surf3").set("expr", "solid.disp");
    model.result("pg11").feature("surf3").set("colortable", "AuroraAustralis");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").create("slc1", "Slice");
    model.result("pg11").feature("slc1").label("\u58f0\u901f");
    model.result("pg11").feature("slc1").set("expr", "ta.v_rms");
    model.result("pg11").feature("slc1").set("quickplane", "zx");
    model.result("pg11").feature("slc1").set("quickynumber", 1);
    model.result("pg11").feature("slc1").set("quickymethod", "coord");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").create("line1", "Line");
    model.result("pg11").feature("line1").set("data", "sec2");
    model.result("pg11").feature("line1").set("expr", "1");
    model.result("pg11").feature("line1").set("coloring", "uniform");
    model.result("pg11").feature("line1").set("color", "black");
    model.result("pg11").feature("line1").create("sel1", "Selection");
    model.result("pg11").feature("line1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("line1").feature("sel1").selection().named("sel8");
    model.result("pg11").run();
    model.result("pg11").set("titletype", "none");

    model.view("view3").set("showgrid", false);

    model.result("pg11").set("showlegends", false);
    model.result("pg11").run();

    model.title("\u6ed1\u79fb\u58c1\u6761\u4ef6\u7684 MEMS \u9ea6\u514b\u98ce");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u5728\u9891\u57df\u4e2d\u6c42\u89e3\u7684 MEMS \u9ea6\u514b\u98ce\u6a21\u578b\uff0c\u5176\u4e2d\u5305\u542b\u76f4\u6d41\u9884\u5e94\u529b\u6548\u5e94\u3002\u6a21\u578b\u662f\u4f7f\u7528\u201c\u673a\u7535\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u3001\u201c\u70ed\u9ecf\u6027\u58f0\u5b66\u201d\u548c\u201c\u538b\u529b\u58f0\u5b66\u201d\u5efa\u7acb\u7684\u3002\u9ea6\u514b\u98ce\u7531\u7a7f\u5b54\u677f\u548c\u9884\u5e94\u529b\u819c\u7ec4\u6210\u3002\u6a21\u578b\u4e2d\u5f15\u5165\u4e86\u201c\u6ed1\u79fb\u58c1\u201d\u8fb9\u754c\u6761\u4ef6\u4ee5\u8003\u8651\u9ad8\u514b\u52aa\u68ee\u6570\u5bf9 MEMS \u9ea6\u514b\u98ce\u7684\u5f71\u54cd\uff0c\u8fd9\u79cd\u6ed1\u79fb\u901f\u5ea6\u5bf9\u4e8e\u901a\u8fc7\u5fae\u7a7f\u5b54\u677f (MPP) \u4e2d\u5fae\u5b54\u7684\u6d41\u52a8\u4ee5\u53ca\u7a7f\u5b54\u677f\u4e0e\u819c\u4e4b\u95f4\u7684\u6324\u538b\u6d41\u52a8\u975e\u5e38\u91cd\u8981\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("mems_microphone_slip_wall.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
