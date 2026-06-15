/*
 * air_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class air_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Single-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkomega", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("cell_type", "1", "\u7c7b\u578b\uff1a1 - \u77e9\u5f62\uff0c2 - \u692d\u5706");
    model.param().set("N_h", "3", "\u6c34\u5e73\u5355\u5143\u6570");
    model.param().set("N_v", "N_h", "\u5782\u76f4\u5355\u5143\u6570");
    model.param().set("D_frame", "1.5[mm]", "\u6846\u67b6\u6df1\u5ea6");
    model.param().set("Th_h", "D_frame", "\u6c34\u5e73\u6846\u67b6\u539a\u5ea6");
    model.param().set("Th_v", "D_frame", "\u5782\u76f4\u6846\u67b6\u539a\u5ea6");
    model.param().set("D_filter", "5[mm]", "\u8fc7\u6ee4\u5668\u6df1\u5ea6");
    model.param().set("L_h", "8[cm]", "\u8fc7\u6ee4\u5668\u6c34\u5e73\u957f\u5ea6");
    model.param().set("L_v", "L_h", "\u8fc7\u6ee4\u5668\u5782\u76f4\u957f\u5ea6");
    model.param().set("U_in", "1[m/s]", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("epsilon_p_i", "0.9", "\u8fc7\u6ee4\u5668\u5b54\u9699\u7387");
    model.param().set("kappa_i", "epsilon_p_i*((1e-4)^2/8)[m^2]", "\u8fc7\u6ee4\u5668\u6e17\u900f\u7387");
    model.param().set("cF_i", "0.1", "\u8fc7\u6ee4\u5668 Forchheimer \u7cfb\u6570");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("L_pre", "2[cm]", "\u5165\u53e3 x \u5750\u6807");
    model.param("par2").set("L_post", "(4*(cell_type==1)+2*(cell_type==2))*L_wake", "\u51fa\u53e3 x \u5750\u6807");
    model.param("par2").set("cL_h", "(L_h-(N_h+1)*Th_h)/N_h", "\u5355\u5143\u6c34\u5e73\u5c3a\u5bf8");
    model.param("par2").set("cL_v", "(L_v-(N_v+1)*Th_v)/N_v", "\u5355\u5143\u5782\u76f4\u5c3a\u5bf8");
    model.param("par2")
         .set("L_wake_1a", "10*max(Th_h,Th_v)", "\u6805\u683c\u5f71\u54cd\u957f\u5ea6\uff0c\u77e9\u5f62 a");
    model.param("par2")
         .set("L_wake_1b", "10*(L_h*L_v/N_h/N_v-cL_h*cL_v)/(cL_h+cL_v)", "\u6805\u683c\u5f71\u54cd\u957f\u5ea6\uff0c\u77e9\u5f62 b");
    model.param("par2")
         .set("L_wake_2", "10*sqrt(L_h*L_v/N_h/N_v-cL_h*cL_v)", "\u6805\u683c\u5f71\u54cd\u957f\u5ea6\uff0c\u692d\u5706");
    model.param("par2")
         .set("L_wake", "(cell_type==1)*L_wake_1a+(cell_type==2)*(L_wake_1b+L_wake_2)/2", "\u6805\u683c\u5f71\u54cd\u957f\u5ea6");
    model.param("par2")
         .set("Cut_v_cell", "round((N_v+1)/2)*(cL_v+Th_v)-cL_v/2", "\u5782\u76f4\u5207\u5272\u5c3a\u5bf8\uff0c\u5355\u5143");
    model.param("par2")
         .set("Cut_h_cell", "round((N_h+1)/2)*(cL_h+Th_h)-cL_h/2", "\u6c34\u5e73\u5207\u5272\u5c3a\u5bf8\uff0c\u5355\u5143");
    model.param("par2")
         .set("Cut_v_edge", "round((N_v+1)/2)*(cL_v+Th_v)+Th_v/2", "\u5782\u76f4\u5207\u5272\u5c3a\u5bf8\uff0c\u8fb9");
    model.param("par2")
         .set("Cut_h_edge", "round((N_h+1)/2)*(cL_h+Th_h)+Th_h/2", "\u6c34\u5e73\u5207\u5272\u5c3a\u5bf8\uff0c\u8fb9");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("if1").set("condition", "cell_type==1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"cL_h", "cL_v"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"Th_h+cL_h/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("pos", "Th_v+cL_v/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection("csel1")
         .label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("elseif1").set("condition", "cell_type==2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("elseif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxes", new String[]{"cL_h/2", "cL_v/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("pos", new String[]{"Th_h+cL_h/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").setIndex("pos", "Th_v+cL_v/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("endif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"N_h", "N_v"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"cL_h+Th_h", "cL_v+Th_v"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"L_h", "L_v"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "D_frame", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").feature("ext1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("ext1").set("customcolor", new double[]{1, 0, 0});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"D_filter", "L_h", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "L_v", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-D_filter", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("blk1").set("color", "4");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u7d2f\u79ef\u9009\u62e9 2");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel2");

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").set("transparency", true);

    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"L_pre+L_post", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "L_h", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "L_v", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-L_pre", "0", "0"});
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u7d2f\u79ef\u9009\u62e9 3");
    model.component("comp1").geom("geom1").feature("blk2").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"-2*D_filter", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"D_filter", "L_h", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "L_v", 2);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u7d2f\u79ef\u9009\u62e9 4");
    model.component("comp1").geom("geom1").feature("blk3").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"D_frame+L_wake", "L_h", "L_v"});
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u7d2f\u79ef\u9009\u62e9 5");
    model.component("comp1").geom("geom1").feature("blk4").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk2", "blk4");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 1, 5);
    model.component("comp1").geom("geom1").run("mcd1");

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
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa_i"});
    model.component("comp1").material("mat1").propertyGroup("def").set("porosity", new String[]{"epsilon_p_i"});

    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u8fc7\u6ee4\u5668\u6b63\u9762");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"geom1_csel2_bnd", "geom1_csel4_bnd"});
    model.component("comp1").selection().create("int2", "Intersection");
    model.component("comp1").selection("int2").label("\u6ee4\u6846");
    model.component("comp1").selection("int2").set("entitydim", 2);
    model.component("comp1").selection("int2").set("input", new String[]{"geom1_csel2_bnd", "geom1_csel5_bnd"});

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("EnablePorousMediaDomains", true);
    model.component("comp1").physics("spf").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("spf").feature("porous1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("spf").feature("porous1").feature("pm1").set("cf", "cF_i");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "U_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(62);

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_csel1_bnd");
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("int2");
    model.component("comp1").mesh("mesh1").run("size2");
    model.component("comp1").mesh("mesh1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(6, 71, 72);
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run("size3");
    model.component("comp1").mesh("mesh1").feature().duplicate("size4", "size3");
    model.component("comp1").mesh("mesh1").feature("size4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size4").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view().create("view4", "geom1");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 17, 18, 19, 21, 22, 23, 24, 25, 26, 28, 29, 30, 32, 33, 34, 36, 37, 38, 39, 40, 41, 43, 44, 45, 47, 48, 49, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L_h", 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", "Cut_v_cell", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", "Cut_v_cell", 1, 2);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("view", "new");
    model.result("pg4").label("\u901f\u5ea6\u548c\u538b\u529b");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "plasticshiny");
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("colortable", "ThermalWave");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().named("int1");
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("number", 100);
    model.result("pg4").feature("str1").set("startdata", "cln1");
    model.result("pg4").feature("str1").set("linetype", "ribbon");
    model.result("pg4").feature("str1").set("widthscaleactive", true);
    model.result("pg4").feature("str1").set("widthscale", "2E-4");
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("col1").set("expr", "p");
    model.result("pg4").feature("str1").feature("col1").set("titletype", "auto");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "Prism");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u901f\u5ea6\u548c\u6e4d\u6d41\u52a8\u80fd");
    model.result("pg5").run();
    model.result("pg5").feature("str1").feature("col1").set("expr", "k");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u901f\u5ea6\u5207\u9762 xy \u548c xz");
    model.result("pg6").set("view", "new");
    model.result("pg6").run();

    model.view("view6").set("transparency", true);
    model.view("view6").set("transparencylevel", 0.1);

    model.result("pg6").run();
    model.result("pg6").feature().remove("surf2");
    model.result("pg6").feature().remove("str1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("quickplane", "xy");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("slc1").set("quickzmethod", "coord");
    model.result("pg6").feature("slc1").set("quickz", "Cut_v_cell");
    model.result("pg6").feature("slc1").set("rangecoloractive", true);
    model.result("pg6").feature("slc1").set("rangecolormax", 1.5);
    model.result("pg6").feature("slc1").set("titletype", "manual");
    model.result("pg6").feature("slc1").set("title", "\u5207\u9762\uff1a\u901f\u5ea6\u5927\u5c0f (m/s)");
    model.result("pg6").feature().duplicate("slc2", "slc1");
    model.result("pg6").run();
    model.result("pg6").feature("slc2").set("quickplane", "zx");
    model.result("pg6").feature("slc2").set("quickymethod", "coord");
    model.result("pg6").feature("slc2").set("quicky", "Cut_h_edge");
    model.result("pg6").feature("slc2").set("inheritplot", "slc1");
    model.result("pg6").feature("slc2").set("titletype", "none");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("log10\uff08\u6e4d\u52a8\u80fd\uff09\u5207\u9762 xy \u548c xz");
    model.result("pg7").run();
    model.result("pg7").feature("slc1").set("rangecoloractive", false);
    model.result("pg7").feature("slc1").set("title", "\u5207\u9762\uff1alog10\uff08\u6e4d\u52a8\u80fd\uff09");
    model.result("pg7").feature("slc1").set("expr", "log10(k)");
    model.result("pg7").feature("slc1").set("colortable", "DipoleDark");
    model.result("pg7").run();
    model.result("pg7").feature("slc2").set("expr", "log10(k)");
    model.result("pg6").run();
    model.result().duplicate("pg8", "pg6");
    model.result("pg8").run();
    model.result("pg8").set("view", "new");
    model.result("pg8").label("\u901f\u5ea6\u5207\u9762 yz \u5e73\u9762");
    model.result("pg8").set("plotarrayenable", true);
    model.result("pg8").set("arrayshape", "square");
    model.result("pg8").set("arrayplane", "yz");
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().remove("surf1");
    model.result("pg8").feature().remove("slc2");
    model.result("pg8").feature("slc1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("slc1").set("quickplane", "yz");
    model.result("pg8").feature("slc1").set("quickxmethod", "coord");
    model.result("pg8").feature("slc1").set("quickx", "-2*D_filter");
    model.result("pg8").feature("slc1").set("manualindexing", true);
    model.result("pg8").feature().duplicate("slc2", "slc1");
    model.result("pg8").feature("slc2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("slc2").set("quickx", "-D_filter");
    model.result("pg8").feature("slc2").set("rowindex", -1);
    model.result("pg8").feature("slc2").set("inheritplot", "slc1");
    model.result("pg8").feature("slc2").set("titletype", "none");
    model.result("pg8").feature().duplicate("slc3", "slc2");
    model.result("pg8").feature("slc3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("slc3").set("quickx", 0);
    model.result("pg8").feature("slc3").set("rowindex", -2);
    model.result("pg8").feature().duplicate("slc4", "slc3");
    model.result("pg8").feature("slc4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("slc4").set("quickx", "D_frame");
    model.result("pg8").feature("slc4").set("rowindex", 0);
    model.result("pg8").feature("slc4").set("colindex", 1);
    model.result("pg8").feature().duplicate("slc5", "slc4");
    model.result("pg8").feature("slc5").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("slc5").set("quickx", "D_frame+L_wake/2");
    model.result("pg8").feature("slc5").set("rowindex", -1);
    model.result("pg8").feature().duplicate("slc6", "slc5");
    model.result("pg8").feature("slc6").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("slc6").set("quickx", "D_frame+L_wake");
    model.result("pg8").feature("slc6").set("rowindex", -2);
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u6e4d\u52a8\u80fd yz \u5e73\u9762");
    model.result("pg9").feature("slc1").set("arraydim", "2");
    model.result("pg9").run();
    model.result("pg9").feature("slc1")
         .set("title", "\u5207\u9762\uff1a\u6e4d\u52a8\u80fd (m<sup>2</sup>/s<sup>2</sup>)");
    model.result("pg9").feature("slc1").set("rangecoloractive", false);
    model.result("pg9").feature("slc1").set("expr", "k");
    model.result("pg9").feature("slc1").set("colortable", "GrayBodyLight");
    model.result("pg9").feature("slc2").set("arraydim", "2");
    model.result("pg9").run();
    model.result("pg9").feature("slc2").set("expr", "k");
    model.result("pg9").feature("slc3").set("arraydim", "2");
    model.result("pg9").run();
    model.result("pg9").feature("slc3").set("expr", "k");
    model.result("pg9").feature("slc4").set("arraydim", "2");
    model.result("pg9").run();
    model.result("pg9").feature("slc4").set("expr", "k");
    model.result("pg9").feature("slc5").set("arraydim", "2");
    model.result("pg9").run();
    model.result("pg9").feature("slc5").set("expr", "k");
    model.result("pg9").feature("slc6").set("arraydim", "2");
    model.result("pg9").run();
    model.result("pg9").feature("slc6").set("expr", "k");
    model.result().export().create("img1", "Image");
    model.result().export("img1").set("size", "current");
    model.result().export("img1").set("unit", "px");
    model.result().export("img1").set("height", "600");
    model.result().export("img1").set("width", "800");
    model.result().export("img1").set("lockratio", "off");
    model.result().export("img1").set("resolution", "96");
    model.result().export("img1").set("antialias", "on");
    model.result().export("img1").set("zoomextents", "off");
    model.result().export("img1").set("fontsize", "9");
    model.result().export("img1").set("colortheme", "globaltheme");
    model.result().export("img1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("img1").set("background", "color");
    model.result().export("img1").set("gltfincludelines", "on");
    model.result().export("img1").set("title1d", "on");
    model.result().export("img1").set("legend1d", "on");
    model.result().export("img1").set("logo1d", "on");
    model.result().export("img1").set("options1d", "on");
    model.result().export("img1").set("title2d", "on");
    model.result().export("img1").set("legend2d", "on");
    model.result().export("img1").set("logo2d", "on");
    model.result().export("img1").set("options2d", "off");
    model.result().export("img1").set("title3d", "on");
    model.result().export("img1").set("legend3d", "on");
    model.result().export("img1").set("logo3d", "on");
    model.result().export("img1").set("options3d", "off");
    model.result().export("img1").set("axisorientation", "on");
    model.result().export("img1").set("grid", "on");
    model.result().export("img1").set("axes1d", "on");
    model.result().export("img1").set("axes2d", "on");
    model.result().export("img1").set("showgrid", "on");
    model.result().export("img1").set("target", "file");
    model.result().export("img1").set("qualitylevel", "92");
    model.result().export("img1").set("qualityactive", "off");
    model.result().export("img1").set("imagetype", "png");
    model.result().export("img1").set("lockview", "off");
    model.result().export("img1").set("size", "presentation");
    model.result().export("img1").set("unit", "px");
    model.result().export("img1").set("lockratio", "off");
    model.result().export("img1").set("width", "874");
    model.result().export("img1").set("height", "656");
    model.result().export("img1").set("resolution", "96");
    model.result().export("img1").set("zoomextents", "off");
    model.result().export("img1").set("antialias", "on");
    model.result().export("img1").set("options1d", "on");
    model.result().export("img1").set("options2d", "off");
    model.result().export("img1").set("options3d", "off");
    model.result().export("img1").set("title1d", "on");
    model.result().export("img1").set("title2d", "on");
    model.result().export("img1").set("title3d", "on");
    model.result().export("img1").set("legend1d", "on");
    model.result().export("img1").set("legend2d", "on");
    model.result().export("img1").set("legend3d", "on");
    model.result().export("img1").set("axes1d", "on");
    model.result().export("img1").set("axes2d", "on");
    model.result().export("img1").set("logo1d", "on");
    model.result().export("img1").set("logo2d", "on");
    model.result().export("img1").set("logo3d", "on");
    model.result().export("img1").set("showgrid", "on");
    model.result().export("img1").set("axisorientation", "on");
    model.result().export("img1").set("grid", "on");
    model.result().export("img1").set("fontsize", "12");
    model.result().export("img1").set("colortheme", "globaltheme");
    model.result().export("img1").set("background", "transparent");
    model.result().export("img1").set("gltfincludelines", "on");
    model.result().export("img1").set("qualitylevel", "92");
    model.result().export("img1").set("qualityactive", "off");
    model.result().export("img1").set("imagetype", "png");
    model.result().export("img1").set("target", "linked");
    model.result().export("img1").set("addsuffix", "off");
    model.result().export("img1").set("lockview", "off");
    model.result().export("img1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("img1").set("width", 3165);
    model.result().export("img1").set("height", 2374);
    model.result().export("img1").set("resolution", 90);
    model.result().export("img1").set("sourcetype", "mesh");
    model.result().export("img1").set("sourceobject", "mesh1");
    model.result().export("img1").set("target", "file");
    model.result().export("img1").set("view", "view3");
    model.result().export("img1").set("pngfilename", "air_filter_mesh_1_HiRes.png");
    model.result().export("img1").run();
    model.result().export().duplicate("img2", "img1");
    model.result().export("img2").set("view", "view4");
    model.result().export("img2").set("pngfilename", "air_filter_mesh_2_HiRes.png");
    model.result().export("img2").run();

    model.title("\u7a7a\u6c14\u8fc7\u6ee4\u5668");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u5305\u542b\u7a7a\u6c14\u8fc7\u6ee4\u5668\u7684\u51e0\u4f55\u4e2d\u7684\u6e4d\u6d41\u3002\u8be5\u8fc7\u6ee4\u5668\u7684\u591a\u5b54\u6750\u6599\u4f1a\u5bfc\u81f4\u5176\u5185\u90e8\u7684\u538b\u529b\u7a81\u7136\u4e0b\u964d\uff0c\u5e76\u4f7f\u6e4d\u6d41\u6c34\u5e73\u6025\u5267\u589e\u52a0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("air_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
