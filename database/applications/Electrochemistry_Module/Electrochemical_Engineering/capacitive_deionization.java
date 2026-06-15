/*
 * capacitive_deionization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:53 by COMSOL 6.3.0.290. */
public class capacitive_deionization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cNap");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cNap", "cClm"});
    model.component("comp1").physics().create("br", "PorousMediaFlowBrinkman", "geom1");
    model.component("comp1").physics().create("dode", "DomainODE", "geom1");
    model.component("comp1").physics("dode").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("dode").prop("Units").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("dode").prop("Units").set("CustomDependentVariableUnit", "V");
    model.component("comp1").physics("dode").field("dimensionless").field("phil_micro");
    model.component("comp1").physics("dode").field("dimensionless").component(new String[]{"phil_micro"});
    model.component("comp1").physics("dode").prop("Units").set("CustomSourceTermUnit", "A/m^3");
    model.component("comp1").physics().create("dode2", "DomainODE", "geom1");
    model.component("comp1").physics("dode2").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("dode2").field("dimensionless").field("mu");
    model.component("comp1").physics("dode2").field("dimensionless").component(new String[]{"mu"});
    model.component("comp1").physics("dode2").prop("Units").set("CustomSourceTermUnit", 1);

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/br", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dode", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dode2", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("h_gap", "0.9[mm]", "\u7535\u6781\u95f4\u8ddd");
    model.param().set("L_electrode", "100[mm]", "\u7535\u6781\u957f\u5ea6");
    model.param().set("L_pipe", "L_electrode/8", "\u5165\u53e3\u548c\u51fa\u53e3\u7ba1\u9053\u957f\u5ea6");
    model.param().set("h_porous", "0.7[mm]", "\u591a\u5b54\u7535\u6781\u539a\u5ea6");
    model.param().set("L_system", "L_pipe*2+L_electrode", "\u7cfb\u7edf\u603b\u957f");
    model.param().set("d_z", "h_porous", "\u9762\u5916\u539a\u5ea6");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("c_NaCl", "20[mM]", "\u521d\u59cb\u548c\u5165\u53e3\u76d0\u6d53\u5ea6");
    model.param("par2").set("DNa", "1.9e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cNa+");
    model.param("par2").set("DCl", "2e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cCl-");
    model.param("par2").set("z_Nap", "+1", "\u7535\u8377\uff0cNa+");
    model.param("par2").set("z_Clm", "-1", "\u7535\u8377\uff0cCl-");
    model.param().create("par3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3")
         .set("alpha", "30[F*m^3/mol^2]", "\u5b54\u9699\u7535\u8377\u4e0e\u8868\u9762\u7535\u5bb9\u7684\u6bd4\u7387");
    model.param("par3").set("C_ref", "150[MF/m^3]", "\u4f53\u79ef\u6bd4\u7535\u5bb9");
    model.param("par3").set("eps_s", "0.4", "\u7535\u6781\u5b54\u9699\u7387");
    model.param("par3").set("epsl_micro", "0.3", "\u5fae\u5b54\u9699\u7684\u4f53\u79ef\u5206\u6570");
    model.param("par3").set("kappa_s", "1e-11[m^2]", "\u7535\u6781\u6e17\u900f\u7387");
    model.param().create("par4");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4")
         .set("E", "z_Nap^2*root.k_B_const*T*lambda_B/lambda_p^4", "\u865a\u529b\u5f15\u8d77\u7684\u5438\u5f15\u80fd\u91cf");
    model.param("par4")
         .set("lambda_B", "root.e_const^2/(4*pi*root.epsilon0_const*root.k_B_const*T)", "\u5fae\u5b54\u9699\u4e2d\u7684 Bjerrum \u957f\u5ea6");
    model.param("par4").set("lambda_p", "4.2[nm]", "\u5fae\u5b54\u9699\u5c3a\u5bf8");
    model.param("par4").set("mu_0", "1.57", "\u5438\u5f15\u52bf\u7684\u521d\u59cb\u503c");
    model.param().create("par5");

//    To import content from file, use:
//    model.param("par5").loadFile("FILENAME");
    model.param("par5").set("A_in", "h_gap*d_z", "\u5165\u53e3\u9762\u79ef");
    model.param("par5").set("u_in", "5e-4[m/s]", "\u5165\u53e3\u6d41\u901f");
    model.param("par5").set("Q", "u_in*A_in", "\u5165\u53e3\u4f53\u79ef\u6d41\u7387");
    model.param().create("par6");

//    To import content from file, use:
//    model.param("par6").loadFile("FILENAME");
    model.param("par6").set("V_max", "0.4[V]", "\u5916\u52a0\u5cf0\u503c\u7535\u538b");
    model.param("par6").set("T", "20[degC]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().create("par7");

//    To import content from file, use:
//    model.param("par7").loadFile("FILENAME");
    model.param("par7")
         .set("elem_porous_count", "9", "\u591a\u5b54\u7535\u6781\u6df1\u5ea6\u65b9\u5411\u7684\u5355\u5143\u6570");
    model.param("par7")
         .set("elem_length_count", "50", "\u6cbf\u7535\u6781\u957f\u5ea6\u65b9\u5411\u7684\u5355\u5143\u6570");
    model.param("par7").set("elem_pipe_count", "8", "\u7ba1\u9053\u6a2a\u622a\u9762\u4e0a\u7684\u5355\u5143\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("R_Nap_macro", "-epsl_micro*d(cNap_micro,TIME)", "Na+ \u7684\u6e90\u9879\uff0c\u5927\u5b54\u9699");
    model.component("comp1").variable("var1")
         .set("R_Clm_macro", "-epsl_micro*d(cClm_micro,TIME)", "Cl- \u7684\u6e90\u9879\uff0c\u5927\u5b54\u9699");
    model.component("comp1").variable("var1")
         .set("iv_dl_helmholtz", "d(C*(phis-phil_micro),TIME)", "\u4ea5\u59c6\u970d\u5179\u5c42\u5145\u7535\u7684\u4f53\u79ef\u53cc\u5c42\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("iv_dl_diffuse", "d(-epsl_micro*F_const*(charge_sum),TIME)", "\u6269\u6563\u53cc\u7535\u5c42\u5145\u7535\u7684\u4f53\u79ef\u53cc\u5c42\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("charge_sum", "(z_Nap*cNap_micro+z_Clm*cClm_micro)", "\u7535\u8377\u603b\u548c\uff0c\u5fae\u5b54\u9699");
    model.component("comp1").variable("var1")
         .set("cNap_micro", "cNap*exp(root.z_Nap*F_const*(phil-phil_micro)/(R_const*T) +mu)", "Na+ \u6d53\u5ea6\uff0c\u5fae\u5b54\u9699");
    model.component("comp1").variable("var1")
         .set("cClm_micro", "cClm*exp(root.z_Clm*F_const*(phil-phil_micro)/(R_const*T) +mu)", "Cl- \u6d53\u5ea6\uff0c\u5fae\u5b54\u9699");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("V_app", "V_max*rect1((t-0.5[h])/1[h])");
    model.component("comp1").variable("var2").descr("V_app", "Applied Potential");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3")
         .set("c_out_Clm", "intop_outlet(tcd.ntflux_cClm*d_z)/intop_outlet(u*d_z)");
    model.component("comp1").variable("var3").descr("c_out_Clm", "Outlet concentration, Cl-");
    model.component("comp1").variable("var3")
         .set("c_out_Nap", "intop_outlet(tcd.ntflux_cNap*d_z)/intop_outlet(u*d_z)");
    model.component("comp1").variable("var3").descr("c_out_Nap", "Outlet concentration, Na+");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").set("csum", "cNap_micro+cClm_micro");
    model.component("comp1").variable("var4").descr("csum", "Sum of micropore concentrations");
    model.component("comp1").variable("var4").set("mu_att", "E/csum/root.k_B_const/T/root.N_A_const");
    model.component("comp1").variable("var4").descr("mu_att", "Nonelectrostatic adsorption potential");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").set("C", "C_ref+alpha*charge_sum^2");
    model.component("comp1").variable("var5").descr("C", "Stern layer capacitance");
    model.component("comp1").variable().create("var6");
    model.component("comp1").variable("var6").set("n_Clm_upper", "intop_upper_electrode(cClm_micro*d_z)");
    model.component("comp1").variable("var6").descr("n_Clm_upper", "Amount of Cl-, upper electrode");
    model.component("comp1").variable("var6").set("n_Clm_lower", "intop_lower_electrode(cClm_micro*d_z)");
    model.component("comp1").variable("var6").descr("n_Clm_lower", "Amount of Cl-, lower electrode");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", 0);
    model.component("comp1").func("rect1").set("upper", 1);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_upper_electrode");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").set("opname", "intop_lower_electrode");
    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").set("opname", "maxop_upper");
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").set("opname", "minop_upper");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_outlet");

    model.component("comp1").view("view1").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view1").axis().set("yscale", 30);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L_electrode", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "h_porous", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"L_electrode", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "h_gap", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "h_porous"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"L_electrode", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "h_porous", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "h_porous+h_gap"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"L_pipe", "h_gap"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-L_pipe", "h_porous"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"L_pipe", "h_gap"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"L_electrode", "0"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("pos", "h_porous", 1);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 6, 12);
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop2").selection().set(2);
    model.component("comp1").cpl("maxop1").selection().set(3);
    model.component("comp1").cpl("minop1").selection().set(3);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop3").selection().set(14);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").physics("tcd").prop("dz").set("dz", "d_z");
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Nap", 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Clm", 1);
    model.component("comp1").physics("tcd").feature("ice1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cNap", new String[]{"DNa", "0", "0", "0", "DNa", "0", "0", "0", "DNa"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cClm", new String[]{"DCl", "0", "0", "0", "DCl", "0", "0", "0", "DCl"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_NaCl", 1);
    model.component("comp1").physics("tcd").create("pce1", "PorousElectrode", 2);
    model.component("comp1").physics("tcd").feature("pce1").selection().set(2, 3);
    model.component("comp1").physics("tcd").feature("pce1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cNap", new String[]{"DNa", "0", "0", "0", "DNa", "0", "0", "0", "DNa"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cClm", new String[]{"DCl", "0", "0", "0", "DCl", "0", "0", "0", "DCl"});
    model.component("comp1").physics("tcd").feature("pce1").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce1").set("sigma", new int[]{100, 0, 0, 0, 100, 0, 0, 0, 100});
    model.component("comp1").physics("tcd").feature("pce1").set("epss", "eps_s");
    model.component("comp1").physics("tcd").feature("pce1").set("epsl", "1-eps_s");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").active(false);
    model.component("comp1").physics("tcd").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcd").feature("in1").selection().set(1);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "root.c_NaCl", 1);
    model.component("comp1").physics("tcd").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcd").feature("out1").selection().set(14);
    model.component("comp1").physics("tcd").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("tcd").feature("egnd1").selection().set(5);
    model.component("comp1").physics("tcd").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("tcd").feature("pot1").selection().set(9);
    model.component("comp1").physics("tcd").feature("pot1").set("phisbnd", "V_app");
    model.component("comp1").physics("tcd").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tcd").feature("reac1").selection().set(2, 3);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cNap", "R_Nap_macro", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cClm", "R_Clm_macro", 0);
    model.component("comp1").physics("tcd").feature("reac1").set("ReactingVolumeType", "TotalVolume");
    model.component("comp1").physics("br").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("br").feature("porous1").feature("pm1").set("epsilon_p", "eps_s");
    model.component("comp1").physics("br").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("br").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa_s", "0", "0", "0", "kappa_s", "0", "0", "0", "kappa_s"});
    model.component("comp1").physics("br").feature("init1").set("u_init", new String[]{"u_in", "0", "0"});
    model.component("comp1").physics("br").create("fp1", "FluidProperties", 2);
    model.component("comp1").physics("br").feature("fp1").selection().set(1);
    model.component("comp1").physics("br").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("br").feature("inl1").selection().set(1);
    model.component("comp1").physics("br").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("br").feature("inl1").set("Uavfdf", "u_in");
    model.component("comp1").physics("br").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("br").feature("out1").selection().set(14);
    model.component("comp1").physics("br").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("dode").selection().set(2, 3);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "iv_dl_helmholtz-iv_dl_diffuse", 0);
    model.component("comp1").physics("dode").feature("dode1").setIndex("da", 0, 0);
    model.component("comp1").physics("dode2").selection().set(2, 3);
    model.component("comp1").physics("dode2").feature("dode1").setIndex("f", "mu_att-mu", 0);
    model.component("comp1").physics("dode2").feature("dode1").setIndex("da", 0, 0);
    model.component("comp1").physics("dode2").feature("init1").set("mu", "mu_0");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "default");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 5);
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature().remove("cr1");
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").feature().remove("bl1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(7, 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", "elem_porous_count");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(4, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", "elem_porous_count");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(5, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", "elem_length_count");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(1, 14);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemcount", "elem_pipe_count");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("symmetric", true);

    model.study("std1").feature("stat").setSolveFor("/physics/tcd", false);
    model.study("std1").feature("stat").setSolveFor("/physics/br", false);
    model.study("std1").feature("stat").setSolveFor("/physics/dode", false);
    model.study("std1").feature("stat").setSolveFor("/physics/dode2", false);
    model.study("std1").feature("stat").setSolveFor("/physics/br", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,50[s], 2[h])");
    model.study("std1").feature("time").setSolveFor("/physics/br", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 145, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg1").feature("arws1").set("arrowbase", "center");
    model.result("pg1").feature("arws1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 145, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg2").feature("arws1").set("arrowbase", "center");
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result("pg2").feature("arws1").create("col1", "Color");
    model.result("pg2").feature("arws1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 145, 0);
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"phis"});
    model.result("pg3").create("arws1", "ArrowSurface");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("arws1").set("expr", new String[]{"tcd.Isx", "tcd.Isy"});
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 145, 0);
    model.result("pg4").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tcd.Isx", "tcd.Isy"});
    model.result("pg4").feature("arws1").set("arrowbase", "center");
    model.result("pg4").feature("arws1").set("color", "gray");
    model.result("pg4").feature("arws1").create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("expr", "root.comp1.tcd.IsMag");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 145, 0);
    model.result("pg5").label("\u6d53\u5ea6, Nap (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"cNap"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"tcd.tflux_cNapx", "tcd.tflux_cNapy"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 145, 0);
    model.result("pg6").label("\u6d53\u5ea6, Clm (tcd)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cClm"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"tcd.tflux_cClmx", "tcd.tflux_cClmy"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u901f\u5ea6 (br)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "br.U");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u538b\u529b (br)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("con1", "Contour");
    model.result("pg8").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("expr", "p");
    model.result("pg8").feature("con1").set("number", 40);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("smooth", "internal");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("data", "parent");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 145, 0);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b");
    model.result("pg9").feature("surf1").set("expr", "phil_micro");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").setIndex("looplevel", 145, 0);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b 2");
    model.result("pg10").feature("surf1").set("expr", "mu");
    model.result("pg1").run();
    model.result().configuration().create("ssol1", "SingleSelectSolution");
    model.result().configuration("ssol1").setIndex("looplevel", "interp", 0);
    model.result().configuration("ssol1").set("interp", new int[]{3600});
    model.result("pg1").run();
    model.result("pg1").set("solutionparams", "configuration");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg1").run();
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", 0.002);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg3").run();
    model.result("pg3").set("solutionparams", "configuration");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", 0.003);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg5").run();
    model.result("pg5").set("solutionparams", "configuration");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("colortable", "Baptisia");
    model.result("pg5").run();
    model.result("pg5").feature("str1").set("posmethod", "selection");
    model.result("pg5").feature("str1").set("selnumber", 16);
    model.result("pg5").feature("str1").selection().set(1);
    model.result("pg5").feature("str1").set("arrowscaleactive", true);
    model.result("pg5").feature("str1").set("arrowscale", 0.38);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("solutionparams", "configuration");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("colortable", "Baptisia");
    model.result("pg6").run();
    model.result("pg6").feature("str1").set("posmethod", "selection");
    model.result("pg6").feature("str1").set("selnumber", 16);
    model.result("pg6").feature("str1").selection().set(1);
    model.result("pg6").feature("str1").set("arrowscaleactive", true);
    model.result("pg6").feature("str1").set("arrowscale", 0.38);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").set("solutionparams", "configuration");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("colortable", "Kyanite");
    model.result("pg7").create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"u", "v"});
    model.result("pg7").feature("arwl1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg7").feature("arwl1").set("placement", "uniformani");
    model.result("pg7").feature("arwl1").set("arrowcount", 20);
    model.result("pg7").feature("arwl1").set("scaleactive", true);
    model.result("pg7").feature("arwl1").set("scale", 5);
    model.result("pg7").feature("arwl1").set("color", "gray");
    model.result("pg7").feature("arwl1").create("sel1", "Selection");
    model.result("pg7").feature("arwl1").feature("sel1").selection().set(1, 14);
    model.result("pg7").run();
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").run();
    model.result("pg8").run();
    model.result("pg8").set("solutionparams", "configuration");
    model.result("pg9").run();
    model.result("pg9").set("solutionparams", "configuration");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg10").run();
    model.result("pg10").set("solutionparams", "configuration");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("colortable", "Arctium");
    model.result("pg11").run();
    model.result("pg11").setIndex("looplevel", 73, 0);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "cClm/c_NaCl");
    model.result("pg11").feature("surf1").set("colortable", "MetasepiaBlue");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("twoyaxes", true);
    model.result("pg12").set("yseclabelactive", true);
    model.result("pg12").set("showlegends", false);
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("linewidth", "preference");
    model.result("pg12").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg12").feature("glob1").set("expr", new String[]{});
    model.result("pg12").feature("glob1").setIndex("expr", "V_app", 0);
    model.result("pg12").create("glob2", "Global");
    model.result("pg12").feature("glob2").set("markerpos", "datapoints");
    model.result("pg12").feature("glob2").set("linewidth", "preference");
    model.result("pg12").feature("glob2").set("expr", new String[]{});
    model.result("pg12").feature("glob2").setIndex("expr", "c_out_Clm", 0);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").set("showlegends", true);
    model.result("pg13").set("legendpos", "middleleft");
    model.result("pg13").run();
    model.result("pg13").feature().remove("glob1");
    model.result("pg13").run();
    model.result("pg13").feature("glob2").set("legendmethod", "manual");
    model.result("pg13").feature("glob2").setIndex("legends", "c<sub>out, Cl-</sub>", 0);
    model.result("pg13").create("glob3", "Global");
    model.result("pg13").feature("glob3").set("markerpos", "datapoints");
    model.result("pg13").feature("glob3").set("linewidth", "preference");
    model.result("pg13").feature("glob3").set("plotonsecyaxis", true);
    model.result("pg13").feature("glob3").setIndex("expr", "maxop_upper(mu_att)", 0);
    model.result("pg13").feature("glob3").setIndex("descr", "max(\\mu_att)", 0);
    model.result("pg13").feature("glob3").setIndex("expr", "minop_upper(mu_att)", 1);
    model.result("pg13").feature("glob3").setIndex("descr", "min(\\mu_att)", 1);
    model.result("pg13").feature("glob3").set("linestyle", "dashed");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result().duplicate("pg14", "pg13");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").feature("glob3").setIndex("expr", "maxop_upper(C)", 0);
    model.result("pg14").feature("glob3").setIndex("unit", "MF/m^3", 0);
    model.result("pg14").feature("glob3").setIndex("descr", "max(C)", 0);
    model.result("pg14").feature("glob3").setIndex("expr", "minop_upper(C)", 1);
    model.result("pg14").feature("glob3").setIndex("unit", "MF/m^3", 1);
    model.result("pg14").feature("glob3").setIndex("descr", "min(C)", 1);
    model.result("pg14").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "Q*c_NaCl", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Inlet molar flow", 0);
    model.result().evaluationGroup("eg1").feature("gev1").set("dataseries", "integral");
    model.result().evaluationGroup("eg1").feature("gev1").set("dataseriescumulative", true);
    model.result().evaluationGroup("eg1").create("int1", "IntLine");
    model.result().evaluationGroup("eg1").feature("int1").set("intsurface", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().set(14);
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "tcd.ntflux_cClm*d_z", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Outlet molar flow", 0);
    model.result().evaluationGroup("eg1").feature("int1").set("dataseries", "integral");
    model.result().evaluationGroup("eg1").feature("int1").set("dataseriescumulative", true);
    model.result().evaluationGroup("eg1").create("int2", "IntLine");
    model.result().evaluationGroup("eg1").feature("int2").set("intsurface", true);
    model.result().evaluationGroup("eg1").feature("int2").selection().set(9);
    model.result().evaluationGroup("eg1").feature("int2").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int2").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int2").setIndex("expr", "tcd.nIs*d_z", 0);
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "Boundary current", 0);
    model.result().evaluationGroup("eg1").feature("int2").set("dataseries", "integral");
    model.result().evaluationGroup("eg1").feature("int2").set("dataseriescumulative", true);
    model.result().evaluationGroup("eg1").set("type", "general");
    model.result().evaluationGroup("eg1").set("generalexpr", "-(gev1-int1)*F_const/int2*100");
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg15", "PlotGroup1D");
    model.result("pg15").set("data", "none");
    model.result("pg15").create("tblp1", "Table");
    model.result("pg15").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg15").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg15").feature("tblp1").set("linewidth", "preference");
    model.result("pg15").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg15").run();
    model.result("pg15").feature("tblp1").set("xaxisdata", 1);
    model.result("pg15").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg15").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg15").run();
    model.result("pg15").set("ylabelactive", true);
    model.result("pg15").set("axislimits", true);
    model.result("pg15").set("xmin", 1980);
    model.result("pg15").set("xmax", 5220);
    model.result("pg15").set("ymin", 0);
    model.result("pg15").set("ymax", 100);
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").set("titletype", "none");
    model.result("pg16").set("xlabelactive", true);
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("twoyaxes", true);
    model.result("pg16").set("yseclabelactive", true);
    model.result("pg16").set("legendlayout", "outside");
    model.result("pg16").set("legendposoutside", "bottom");
    model.result("pg16").create("glob1", "Global");
    model.result("pg16").feature("glob1").set("markerpos", "datapoints");
    model.result("pg16").feature("glob1").set("linewidth", "preference");
    model.result("pg16").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg16").feature("glob1").set("expr", new String[]{});
    model.result("pg16").feature("glob1").setIndex("expr", "V_app", 0);
    model.result("pg16").create("glob2", "Global");
    model.result("pg16").feature("glob2").set("markerpos", "datapoints");
    model.result("pg16").feature("glob2").set("linewidth", "preference");
    model.result("pg16").feature("glob2").set("expr", new String[]{});
    model.result("pg16").feature("glob2").setIndex("expr", "n_Clm_upper", 0);
    model.result("pg16").feature("glob2").setIndex("unit", "umol", 0);
    model.result("pg16").feature("glob2").setIndex("descr", "Upper electrode", 0);
    model.result("pg16").feature("glob2").setIndex("expr", "n_Clm_lower", 1);
    model.result("pg16").feature("glob2").setIndex("unit", "umol", 1);
    model.result("pg16").feature("glob2").setIndex("descr", "Lower electrode", 1);
    model.result("pg16").run();
    model.result("pg12").run();

    model.title("\u7535\u5bb9\u53bb\u79bb\u5b50\u6280\u672f\u5728\u76d0\u6c34\u5904\u7406\u4e2d\u7684\u5e94\u7528");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5728\u201c\u6d41\u8fc7\u5f0f\u201d\u5355\u5143 (fbCDI) \u4e2d\uff0c\u901a\u8fc7\u7535\u5bb9\u53bb\u79bb\u5b50\u6280\u672f\u5bf9\u6c34\u8fdb\u884c\u8131\u76d0\u5904\u7406\u7684\u8fc7\u7a0b\u3002\u6a21\u578b\u4e2d\u91c7\u7528\u4e8c\u7ef4\u51e0\u4f55\uff0c\u5e76\u57fa\u4e8e\u7a33\u6001 Brinkman \u6d41\u52a8\u3001\u4e09\u6b21\u7535\u6d41\u5206\u5e03\uff0c\u4ee5\u53ca\u6539\u8fdb\u7684\u4fee\u6b63 Donnan \u6a21\u578b\u7684\u5047\u8bbe\u6765\u63cf\u8ff0\u53bb\u79bb\u5b50\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("capacitive_deionization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
