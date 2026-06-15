/*
 * pyrolysis_wood_odeobj.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:23 by COMSOL 6.3.0.290. */
public class pyrolysis_wood_odeobj {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcs", "ConcentratedSpeciesInPorousMedia", "geom1");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");
    model.component("comp1").physics().create("dode", "DomainODE", "geom1");
    model.component("comp1").physics("dode").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/dode", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.param().label("\u6837\u54c1\u5c5e\u6027");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_sample", "0.5[inch]", "\u6728\u8d28\u6837\u672c\u7684\u534a\u5f84");
    model.param().set("rho_w_init", "630[kg/m^3]", "0 s \u65f6\u7684\u6728\u6750\u5bc6\u5ea6");
    model.param().set("epsilon_w_init", "0.4", "0 s \u65f6\u7684\u6728\u6750\u5b54\u9699\u7387");
    model.param().set("d_w", "5e-5[m]", "\u6728\u6750\u5b54\u9699\u5927\u5c0f");
    model.param().set("d_c", "1e-4[m]", "\u70ad\u7684\u5b54\u9699\u5927\u5c0f");
    model.param().set("Mw_N2", "0.028[kg/mol]", "\u6c2e\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("Mw_g", "0.038[kg/mol]", "\u5047\u6c14\u4f53\u7269\u8d28\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("Mw_t", "0.11[kg/mol]", "\u5047\u7126\u6cb9\u7269\u8d28\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("e", "1", "\u53d1\u5c04\u7387");
    model.param().set("e_w", "0.7", "\u6728\u6750\u53d1\u5c04\u7387");
    model.param().set("e_c", "0.92", "\u70ad\u7684\u53d1\u5c04\u7387");
    model.param().set("rho_water", "1000[kg/m^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param()
         .set("k_c_across", "0.071[W/m/K]", "\u70ad\u5728\u5782\u76f4\u4e8e\u7ea4\u7ef4\u65b9\u5411\u7684\u6709\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("k_c_along", "0.105[W/m/K]", "\u70ad\u5728\u7ea4\u7ef4\u65b9\u5411\u7684\u6709\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("k_w_across", "rho_w_init/rho_water*(0.1941+0.01864) [W/m/K]", "\u6728\u6750\u5728\u5782\u76f4\u4e8e\u7ea4\u7ef4\u65b9\u5411\u7684\u6709\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("k_w_along", "2.8 * k_w_across", "\u6728\u6750\u5728\u7ea4\u7ef4\u65b9\u5411\u7684\u6709\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("k_mixture", "0.0258[W/m/K]", "\u6c14\u4f53\u6df7\u5408\u7269\u7684\u6709\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("viscosity", "3e-5[kg/m/s]", "\u6c14\u4f53\u6df7\u5408\u7269\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param()
         .set("kappa_w_across", "5e-16[m^2]", "\u6728\u6750\u5728\u5782\u76f4\u4e8e\u7ea4\u7ef4\u65b9\u5411\u7684\u6e17\u900f\u7387");
    model.param()
         .set("kappa_w_along", "5e-14[m^2]", "\u6728\u6750\u5728\u7ea4\u7ef4\u65b9\u5411\u7684\u6e17\u900f\u7387");
    model.param()
         .set("kappa_c_across", "1e-13[m^2]", "\u70ad\u5728\u5782\u76f4\u4e8e\u7ea4\u7ef4\u65b9\u5411\u7684\u6e17\u900f\u7387");
    model.param().set("kappa_c_along", "1e-12[m^2]", "\u70ad\u5728\u7ea4\u7ef4\u65b9\u5411\u7684\u6e17\u900f\u7387");
    model.param().create("par2");
    model.param("par2").label("\u5b9e\u9a8c\u6761\u4ef6");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("T0", "310[K]", "\u521d\u59cb\u6837\u54c1\u6e29\u5ea6");
    model.param("par2").set("T_furnace", "736[K]", "\u7089\u6e29");
    model.param("par2").set("T_gas", "670[K]", "\u53cd\u5e94\u5668\u4e2d\u6c14\u4f53\u6e29\u5ea6");
    model.param("par2").set("hconv", "hconv_0*exp(hconv_opt)", "\u5916\u90e8\u5bf9\u6d41\u6362\u70ed\u7cfb\u6570");
    model.param("par2").set("hconv_0", "5[W/m^2/K]", "\u7269\u7406\u53c2\u6570\u521d\u59cb\u503c");
    model.param("par2").set("hconv_lower", "1[W/m^2/K]", "\u7269\u7406\u53c2\u6570\u4e0b\u9650");
    model.param("par2").set("hconv_upper", "100[W/m^2/K]", "\u7269\u7406\u53c2\u6570\u4e0a\u9650");
    model.param("par2").set("hconv_opt", "0", "\u5f85\u4f18\u5316\u63a7\u5236\u53c2\u6570");
    model.param("par2").set("hconv_opt_0", "log(hconv_0/hconv_0)", "hconv_opt \u521d\u59cb\u503c");
    model.param("par2").set("hconv_opt_lower", "log(hconv_lower/hconv_0)", "hconv_opt \u4e0b\u9650");
    model.param("par2").set("hconv_opt_upper", "log(hconv_upper/hconv_0)", "hconv_opt \u4e0a\u9650");
    model.param().create("par3");
    model.param("par3").label("\u53cd\u5e94\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("DH_t", "DH_t_0*exp(DH_t_opt)", "\u53cd\u5e94\u70ed\uff0c\u6728\u6750 -> \u7126\u6cb9");
    model.param("par3").set("DH_g", "DH_t", "\u53cd\u5e94\u70ed\uff0c\u6728\u6750 -> \u6c14");
    model.param("par3").set("DH_is", "DH_t", "\u53cd\u5e94\u70ed\uff0c\u6728\u6750 -> \u4e2d\u95f4\u56fa\u4f53");
    model.param("par3").set("DH_c2", "-42[kJ/kg]", "\u53cd\u5e94\u70ed\uff0c\u7126\u6cb9 -> \u70ad");
    model.param("par3").set("DH_g2", "DH_c2", "\u53cd\u5e94\u70ed\uff0c\u7126\u6cb9 -> \u6c14");
    model.param("par3")
         .set("DH_c", "DH_c_0*exp(DH_c_opt)", "\u53cd\u5e94\u70ed\uff0c\u4e2d\u95f4\u56fa\u4f53 -> \u70ad");
    model.param("par3")
         .set("A_t", "1.08e10[1/s]", "\u9891\u7387\u56e0\u5b50\uff0cw (\u6728\u6750) -> t (\u7126\u6cb9)");
    model.param("par3").set("E_t", "148[kJ/mol]", "\u6d3b\u5316\u80fd\uff0cw -> t");
    model.param("par3")
         .set("A_is", "A_is_0*exp(A_is_opt)", "\u9891\u7387\u56e0\u5b50\uff0cw -> is (\u4e2d\u95f4\u56fa\u4f53)");
    model.param("par3").set("E_is", "111.7[kJ/mol]", "\u6d3b\u5316\u80fd\uff0cw -> is");
    model.param("par3").set("A_g", "4.38e9[1/s]", "\u9891\u7387\u56e0\u5b50\uff0cw -> g (\u4e3b\u6c14\u4f53)");
    model.param("par3").set("E_g", "152.7[kJ/mol]", "\u6d3b\u5316\u80fd\uff0cw -> g");
    model.param("par3").set("A_c", "1.38e10[1/s]", "\u9891\u7387\u56e0\u5b50\uff0cis-> c (\u70ad)");
    model.param("par3").set("E_c", "161[kJ/mol]", "\u6d3b\u5316\u80fd\uff0cis -> c");
    model.param("par3").set("A_c2", "1.00e5[1/s]", "\u9891\u7387\u56e0\u5b50\uff0ct -> c");
    model.param("par3").set("E_c2", "108[kJ/mol]", "\u6d3b\u5316\u80fd\uff0ct  -> c");
    model.param("par3").set("A_g2", "4.28e6[1/s]", "\u9891\u7387\u56e0\u5b50\uff0ct -> g");
    model.param("par3").set("E_g2", "108[kJ/mol]", "\u6d3b\u5316\u80fd\uff0ct -> g");
    model.param("par3").set("A_is_0", "1e7[1/s]", "\u7269\u7406\u53c2\u6570\u7684\u521d\u59cb\u503c\uff0cA_is");
    model.param("par3").set("A_is_lower", "1e4[1/s]", "\u7269\u7406\u53c2\u6570\u7684\u4e0b\u9650\uff0cA_is");
    model.param("par3").set("A_is_upper", "1e9[1/s]", "\u7269\u7406\u53c2\u6570\u7684\u4e0a\u9650\uff0cA_is");
    model.param("par3").set("A_is_opt", "0", "\u5f85\u4f18\u5316\u7684\u63a7\u5236\u53c2\u6570");
    model.param("par3").set("A_is_opt_0", "log(A_is_0/A_is_0)", "\u521d\u59cb\u503c\uff0cA_is_opt");
    model.param("par3").set("A_is_opt_lower", "log(A_is_lower/A_is_0)", "\u4e0b\u9650\uff0cA_is_opt");
    model.param("par3").set("A_is_opt_upper", "log(A_is_upper/A_is_0)", "\u4e0a\u9650\uff0cA_is_opt");
    model.param("par3").set("DH_t_0", "50[kJ/kg]", "\u7269\u7406\u53c2\u6570\u7684\u521d\u59cb\u503c\uff0cDH_t");
    model.param("par3").set("DH_t_lower", "1[kJ/kg]", "\u7269\u7406\u53c2\u6570\u7684\u4e0b\u9650\uff0cDH_t");
    model.param("par3").set("DH_t_upper", "400[kJ/kg]", "\u7269\u7406\u53c2\u6570\u7684\u4e0a\u9650\uff0cDH_t");
    model.param("par3").set("DH_t_opt", "0", "\u5f85\u4f18\u5316\u7684\u63a7\u5236\u53c2\u6570");
    model.param("par3").set("DH_t_opt_0_", "log(DH_t_0/DH_t_0)", "\u521d\u59cb\u503c\uff0cDH_t_opt");
    model.param("par3").set("DH_t_opt_lower", "log(DH_t_lower/DH_t_0)", "\u4e0b\u9650\uff0cDH_t_opt");
    model.param("par3").set("DH_t_opt_upper", "log(DH_t_upper/DH_t_0)", "\u4e0a\u9650\uff0cDH_t_opt");
    model.param("par3").set("DH_c_0", "-200[kJ/kg]", "\u7269\u7406\u53c2\u6570\u7684\u521d\u59cb\u503c\uff0cDH_c");
    model.param("par3").set("DH_c_lower", "-400[kJ/kg]", "\u7269\u7406\u53c2\u6570\u7684\u4e0b\u9650\uff0cDH_c");
    model.param("par3").set("DH_c_upper", "-10[kJ/kg]", "\u7269\u7406\u53c2\u6570\u7684\u4e0a\u9650\uff0cDH_c");
    model.param("par3").set("DH_c_opt", "0", "\u5f85\u4f18\u5316\u7684\u63a7\u5236\u53c2\u6570");
    model.param("par3").set("DH_c_opt_0_", "log(DH_c_0/DH_c_0)", "\u521d\u59cb\u503c\uff0cDH_c_opt");
    model.param("par3").set("DH_c_opt_lower", "log(DH_c_upper/DH_c_0)", "\u4e0b\u9650\uff0cDH_c_opt");
    model.param("par3").set("DH_c_opt_upper", "log(DH_c_lower/DH_c_0)", "\u4e0a\u9650\uff0cDH_c_opt");
    model.param().create("par4");
    model.param("par4").label("\u4f18\u5316\u53c2\u6570");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("rnPar", "0");
    model.param("par4").set("tStep", "1[s]");
    model.param("par4").set("tmax", "600[s]");
    model.param("par4").set("Tdelta", "400[K]");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u56fa\u4f53\u7269\u8d28\u53d8\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("rho_b", "(1-epsilon)*rho_w_init", "\u5e72\u672c\u4f53\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("d_eff", "d_w*(1-eta) + d_c*eta", "\u6837\u54c1\u4e2d\u7684\u7b49\u6548\u5b54\u9699\u76f4\u5f84");
    model.component("comp1").variable("var1")
         .set("epsilon", "1-(rho_w+rho_is + rho_c)*(1-epsilon_w_init)/rho_w_init", "\u6837\u54c1\u5b54\u9699\u7387");
    model.component("comp1").variable("var1")
         .set("kappa_eff_across", "(rho_w*kappa_w_across + (rho_is + rho_c)*kappa_c_across)/(rho_w + rho_is + rho_c)", "\u5782\u76f4\u7ea4\u7ef4\u65b9\u5411\u7684\u7b49\u6548\u6e17\u900f\u7387");
    model.component("comp1").variable("var1")
         .set("kappa_eff_along", "(rho_w*kappa_w_along + (rho_is + rho_c)*kappa_c_along)/(rho_w + rho_is + rho_c)", "\u6cbf\u7ea4\u7ef4\u65b9\u5411\u7684\u7b49\u6548\u6e17\u900f\u7387");
    model.component("comp1").variable("var1")
         .set("k_eff_along", "(rho_w * k_w_along + (rho_is + rho_c) * k_c_along) / (rho_w + rho_is + rho_c)", "\u6cbf\u7ea4\u7ef4\u65b9\u5411\u7684\u7b49\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("k_eff_across", "(rho_w * k_w_across + (rho_is + rho_c) * k_c_across) / (rho_w + rho_is + rho_c)", "\u5782\u76f4\u7ea4\u7ef4\u65b9\u5411\u7684\u7b49\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("cp_b", "(cp_w*rho_w +cp_c*(rho_is + rho_c))/rho_w_init", "\u5e72\u672c\u4f53\u7684\u6bd4\u70ed\u5bb9");
    model.component("comp1").variable("var1")
         .set("cp_w", "1500[J/kg/K] + 1.0[J/kg/K^2] * T", "\u6728\u6750\u7684\u6bd4\u70ed\u5bb9");
    model.component("comp1").variable("var1")
         .set("cp_c", "420[J/kg/K]  + 2.09[J/kg/K^2] * T + 6.85e-4[J/kg/K^3]  * T^2", "\u4e2d\u95f4\u56fa\u4f53\u548c\u70ad\u7684\u6bd4\u70ed\u5bb9");
    model.component("comp1").variable("var1")
         .set("Y", "(rho_w + rho_is + rho_c) / rho_w_init", "\u5f52\u4e00\u5316\u56fa\u4f53\u8d28\u91cf");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53cd\u5e94\u53d8\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("eta", "1-(rho_w+rho_is)/rho_w_init", "\u70ed\u89e3\u5ea6");
    model.component("comp1").variable("var2")
         .set("k_t", "A_t*exp(-E_t/(R_const*T))", "\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0cw -> t");
    model.component("comp1").variable("var2")
         .set("k_is", "A_is*exp(-E_is/(R_const*T))", "\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0cw -> is");
    model.component("comp1").variable("var2")
         .set("k_g", "A_g*exp(-E_g/(R_const*T))", "\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0cw -> g");
    model.component("comp1").variable("var2")
         .set("k_c", "A_c*exp(-E_c/(R_const*T))", "\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0cis -> c");
    model.component("comp1").variable("var2")
         .set("k_c2", "A_c2*exp(-E_c2/(R_const*T))", "\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0ct -> c");
    model.component("comp1").variable("var2")
         .set("k_g2", "A_g2*exp(-E_g2/(R_const*T))", "\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0ct -> g");
    model.component("comp1").variable("var2").set("Q", "Q_w +Q_c + Q_g2 + Q_c2", "\u603b\u70ed\u6e90");
    model.component("comp1").variable("var2")
         .set("Q_w", "-rho_w*(k_t*DH_t + k_g*DH_g +  k_is*DH_is)", "\u4e3b\u70ed\u89e3\u53cd\u5e94\u7684\u70ed\u6e90");
    model.component("comp1").variable("var2")
         .set("Q_c", "-k_c*rho_is*DH_c", "\u4e2d\u95f4\u56fa\u4f53\u4ea7\u70ad\u7684\u70ed\u6e90");
    model.component("comp1").variable("var2")
         .set("Q_g2", "-k_g2*DH_g2*tcs.rho*w_t", "\u7126\u6cb9\u4ea7\u6c14\u7684\u70ed\u6e90");
    model.component("comp1").variable("var2")
         .set("Q_c2", "-k_c2*DH_c2*tcs.rho*w_t", "\u7126\u6cb9\u4ea7\u70ad\u7684\u70ed\u6e90");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u6d41\u4f53\u7269\u8d28\u53d8\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3")
         .set("k_f", "k_mixture + 13.5*sigma_const*T^3*d_eff/e/epsilon", "\u5b54\u9699\u6d41\u4f53\u7684\u70ed\u5bfc\u7387");
    model.component("comp1").variable("var3")
         .set("cp_f", "w_t * cp_t +w_N2 * cp_N2 + w_g * cp_g", "\u5b54\u9699\u6d41\u4f53\u7684\u6bd4\u70ed\u5bb9");
    model.component("comp1").variable("var3")
         .set("cp_t", "-100[J/kg/K] + 4.4[J/kg/K^2] *T-1.57e-3[J/kg/K^3] *T^2", "\u7126\u6cb9\u7684\u6bd4\u70ed\u5bb9");
    model.component("comp1").variable("var3")
         .set("cp_g", "770[J/kg/K] + 0.629[J/kg/K^2] *T-1.91e-4[J/kg/K^3] *T^2", "\u6c14\u4f53\u7684\u6bd4\u70ed\u5bb9");
    model.component("comp1").variable("var3")
         .set("cp_N2", "950[J/kg/K] + 0.188[J/kg/K^2] * T", "N2 \u7684\u6bd4\u70ed\u5bb9");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u5916\u90e8\u8fb9\u754c\u53d8\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var4").loadFile("FILENAME");
    model.component("comp1").variable("var4")
         .set("e_s", "nif(\nlowT, e_w,\nmidT , e_mix,\ne_c)", "\u968f\u6837\u54c1\u8868\u9762\u7ec4\u5206\u53d8\u5316\u7684\u8868\u9762\u8f90\u5c04\u7387");
    model.component("comp1").variable("var4").set("lowT", "T<450[K]", "\u4f4e\u6e29\u6761\u4ef6");
    model.component("comp1").variable("var4").set("midT", "T >= 450[K] && T <= 550[K]", "\u4e2d\u6e29\u6761\u4ef6");
    model.component("comp1").variable("var4")
         .set("e_mix", "e_w + (T-450[K])/(550[K]-450[K])*(e_c-e_w)", "\u4e2d\u6e29\u6761\u4ef6\u7684\u8868\u9762\u8f90\u5c04\u7387");
    model.component("comp1").variable("var4")
         .set("q0", "hconv * (T_gas - T) + sigma_const * e_s * (T_furnace^4 - T^4)", "\u901a\u8fc7\u6837\u54c1\u5916\u8868\u9762\u7684\u70ed\u901a\u91cf");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_sample");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("r", "r_sample/3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").label("\u6cbf\u4e2d\u95f4");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "r_sample/2", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 7);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").physics("dode").prop("Units").set("DependentVariableQuantity", "density");
    model.component("comp1").physics("dode").prop("Units").setIndex("CustomSourceTermUnit", "kg/(m^3*s)", 0, 0);
    model.component("comp1").physics("dode").field("dimensionless").field("rho");
    model.component("comp1").physics("dode").field("dimensionless").component(new String[]{"u", "rho2", "rho3"});
    model.component("comp1").physics("dode").field("dimensionless").component(1, "rho_w");
    model.component("comp1").physics("dode").field("dimensionless").component(2, "rho_is");
    model.component("comp1").physics("dode").field("dimensionless").component(3, "rho_c");
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "-(k_t+k_g+k_is)*rho_w", 0);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "k_is*rho_w-k_c*rho_is", 1);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "k_c*rho_is+k_c2*tcs.rho*w_t", 2);
    model.component("comp1").physics("dode").feature("init1").set("rho_w", "rho_w_init");
    model.component("comp1").physics("tcs").prop("TransportMechanism").set("DiffusionModel", "FicksLaw");
    model.component("comp1").physics("tcs").field("massfraction").component(new String[]{"w1", "w2", "w3"});
    model.component("comp1").physics("tcs").field("massfraction").component(1, "w_t");
    model.component("comp1").physics("tcs").field("massfraction").component(2, "w_g");
    model.component("comp1").physics("tcs").field("massfraction").component(3, "w_N2");
    model.component("comp1").physics("tcs").prop("SpeciesProperties").set("FromMassConstraint", 3);
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_w_t", "Mw_t", 0);
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_w_g", "Mw_g", 0);
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_w_N2", "Mw_N2", 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tcs").feature("porous1").feature("pm1").set("epsilon_p", "epsilon");
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", 0, 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", 0, 1);
    model.component("comp1").physics("tcs").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("tcs").feature("sym1").selection().set(2, 3);
    model.component("comp1").physics("tcs").create("reac1", "ReactionSources", 2);
    model.component("comp1").physics("tcs").feature("reac1").label("\u76f8\u8f6c\u79fb\u53cd\u5e94\u6e90");
    model.component("comp1").physics("tcs").feature("reac1").selection().set(1);
    model.component("comp1").physics("tcs").feature("reac1").set("MassTransferToOtherPhases", true);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_w_t", "k_t* rho_w-k_c2*w_t*tcs.rho", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_w_g", "k_g*rho_w", 0);
    model.component("comp1").physics("tcs").create("reac2", "ReactionSources", 2);
    model.component("comp1").physics("tcs").feature("reac2")
         .label("\u53cd\u5e94\u6e90\uff1a\u6c14\u4f53\u5230\u6c14\u4f53");
    model.component("comp1").physics("tcs").feature("reac2").selection().set(1);
    model.component("comp1").physics("tcs").feature("reac2").setIndex("R_w_t", "-k_g2*w_t*tcs.rho", 0);
    model.component("comp1").physics("tcs").feature("reac2").setIndex("R_w_g", "k_g2*w_t*tcs.rho", 0);
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "root.comp1.tcs.rho");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu", "viscosity");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "epsilon");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa_eff_along", "0", "0", "0", "kappa_eff_along", "0", "0", "0", "kappa_eff_across"});
    model.component("comp1").physics("dl").create("ms1", "MassSource", 2);
    model.component("comp1").physics("dl").feature("ms1").selection().set(1);
    model.component("comp1").physics("dl").feature("ms1").set("Qm", "tcs.Qmass");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr1").selection().set(4);
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("dl").feature("sym1").selection().set(2, 3);
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("minput_pressure_src", "root.comp1.dl.pA");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("k", new String[]{"k_f", "0", "0", "0", "k_f", "0", "0", "0", "k_f"});
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("rho_mat", "root.comp1.tcs.rho");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("Cp", "cp_f");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro", "epsilon");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("k_b", new String[]{"k_eff_along", "0", "0", "0", "k_eff_along", "0", "0", "0", "k_eff_across"});
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_b", "rho_b");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_b", "cp_b");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("ht").feature("sym1").selection().set(2, 3);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "q0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "Q");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 1);
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 \u6b63\u6f14\u6a21\u578b\uff08\u57fa\u4e8e\u521d\u59cb\u503c\uff09");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,0.01*tmax,tmax)");
    model.study("std1").feature("time").set("probesel", "none");
    model.study("std1").feature("time").setSolveFor("/physics/ge", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_p").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_rho_c").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_rho_c").set("scaleval", "rho_w_init");
    model.sol("sol1").feature("v1").feature("comp1_rho_is").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_rho_is").set("scaleval", "rho_w_init");
    model.sol("sol1").feature("v1").feature("comp1_rho_w").set("scalemethod", "init");
    model.sol("sol1").feature("v1").feature("comp1_T").set("scalemethod", "init");
    model.sol("sol1").feature("v1").feature("comp1_w_g").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_w_g").set("scaleval", 0.1);
    model.sol("sol1").feature("v1").feature("comp1_w_t").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_w_t").set("scaleval", 0.1);

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "table");
    model.nodeGroup("grp1").placeAfter(null);
    model.nodeGroup("grp1").label("\u5b9e\u9a8c\u6570\u636e");

    model.result().table().create("tbl1", "Table");

    model.nodeGroup("grp1").add("table", "tbl1");

    model.result().table("tbl1").importData("pyrolysis_wood_odeobj_experimental_data_Y.txt");
    model.result().table("tbl1").label("\u5b9e\u9a8c\u6570\u636e\uff1aY");
    model.result().table("tbl1").setIndex("headers", "t(s)", 0, 1);
    model.result().table("tbl1").setIndex("headers", "Y", 1, 1);
    model.result().table().create("tbl2", "Table");

    model.nodeGroup("grp1").add("table", "tbl2");

    model.result().table("tbl2").label("\u5b9e\u9a8c\u6570\u636e\uff1aT_surface");
    model.result().table("tbl2").importData("pyrolysis_wood_odeobj_experimental_data_T_surface.txt");
    model.result().table("tbl2").setIndex("headers", "t(s)", 0, 1);
    model.result().table("tbl2").setIndex("headers", "T(K)", 1, 1);
    model.result().table().duplicate("tbl3", "tbl2");

    model.nodeGroup("grp1").add("table", "tbl3");

    model.result().table("tbl3").label("\u5b9e\u9a8c\u6570\u636e\uff1aT_middle");
    model.result().table("tbl3").importData("pyrolysis_wood_odeobj_experimental_data_T_middle.txt");
    model.result().table("tbl3").setIndex("headers", "t(s)", 0, 1);
    model.result().table("tbl3").setIndex("headers", "T(K)", 1, 1);
    model.result().table().duplicate("tbl4", "tbl3");

    model.nodeGroup("grp1").add("table", "tbl4");

    model.result().table("tbl4").label("\u5b9e\u9a8c\u6570\u636e\uff1aT_center");
    model.result().table("tbl4").importData("pyrolysis_wood_odeobj_experimental_data_T_center.txt");
    model.result().table("tbl4").setIndex("headers", "t(s)", 0, 1);
    model.result().table("tbl4").setIndex("headers", "T(K)", 1, 1);
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5b9e\u9a8c\u6570\u636e");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "red");
    model.result("pg1").feature("tblp1").set("linemarker", "plus");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("autoplotlabel", true);
    model.result("pg1").feature("tblp1").set("autoheaders", false);
    model.result("pg1").feature("tblp1").label("\u5f52\u4e00\u5316\u56fa\u4f53\u8d28\u91cf");
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").label("\u8868\u9762\u6e29\u5ea6");
    model.result("pg1").feature("tblp2").set("table", "tbl2");
    model.result("pg1").feature("tblp2").set("linecolor", "magenta");
    model.result("pg1").feature("tblp2").set("linemarker", "asterisk");
    model.result("pg1").feature().duplicate("tblp3", "tblp2");
    model.result("pg1").run();
    model.result("pg1").feature("tblp3").label("\u4e2d\u95f4\u6e29\u5ea6");
    model.result("pg1").feature("tblp3").set("table", "tbl3");
    model.result("pg1").feature("tblp3").set("linecolor", "black");
    model.result("pg1").feature("tblp3").set("linemarker", "circle");
    model.result("pg1").feature().duplicate("tblp4", "tblp3");
    model.result("pg1").run();
    model.result("pg1").feature("tblp4").label("\u4e2d\u5fc3\u6e29\u5ea6");
    model.result("pg1").feature("tblp4").set("table", "tbl4");
    model.result("pg1").feature("tblp4").set("linecolor", "blue");
    model.result("pg1").feature("tblp4").set("linemarker", "point");
    model.result("pg1").run();
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u5f52\u4e00\u5316\u56fa\u4f53\u8d28\u91cf (-)");
    model.result("pg1").set("yseclabelactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").set("yseclabel", "\u6e29\u5ea6 (K)");
    model.result("pg1").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg1").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg1").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").run();

    model.nodeGroup().create("grp2", "Definitions", "comp1");
    model.nodeGroup("grp2").label("\u53c2\u6570\u4f30\u8ba1\u63a2\u9488");

    model.component("comp1").probe().create("point1", "Point");

    model.nodeGroup("grp2").add("probe", "point1");

    model.component("comp1").probe("point1").label("\u70b9\u63a2\u9488\uff0c\u8868\u9762");
    model.component("comp1").probe("point1").set("probename", "T_surface");
    model.component("comp1").probe("point1").selection().set(4);
    model.component("comp1").probe("point1").set("expr", "T");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe().duplicate("point2", "point1");

    model.nodeGroup("grp2").add("probe", "point2");

    model.component("comp1").probe("point2").label("\u70b9\u63a2\u9488\uff0c\u4e2d\u95f4");
    model.component("comp1").probe("point2").set("probename", "T_middle");
    model.component("comp1").probe("point2").selection().set(3);
    model.component("comp1").probe().duplicate("point3", "point2");

    model.nodeGroup("grp2").add("probe", "point3");

    model.component("comp1").probe("point3").label("\u70b9\u63a2\u9488\uff0c\u4e2d\u5fc3");
    model.component("comp1").probe("point3").set("probename", "T_center");
    model.component("comp1").probe("point3").selection().set(1);
    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);

    model.nodeGroup("grp2").add("probe", "dom1");

    model.component("comp1").probe("dom1").label("\u57df\u63a2\u9488 Y");
    model.component("comp1").probe("dom1").set("probename", "domY");
    model.component("comp1").probe("dom1").set("expr", "Y");
    model.component("comp1").probe("dom1").set("descractive", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("type", "general");
    model.result().evaluationGroup("eg1").set("keepchildnodes", true);
    model.result().evaluationGroup("eg1").label("\u8d28\u91cf\u5b88\u6052\u68c0\u67e5");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1")
         .label("\u542b\u6709\u6c14\u4f53\u548c\u7126\u6cb9\u7684\u6837\u54c1");
    model.result().evaluationGroup("eg1").feature("int1").selection().set(1);
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "tcs.rho * (w_t+w_g)*epsilon", 0);
    model.result().evaluationGroup("eg1").feature("int1")
         .setIndex("descr", "\u542b\u6709\u6c14\u4f53\u548c\u7126\u6cb9", 0);
    model.result().evaluationGroup("eg1").create("int2", "IntLine");
    model.result().evaluationGroup("eg1").feature("int2").set("intsurface", true);
    model.result().evaluationGroup("eg1").feature("int2")
         .label("\u4e0d\u542b\u6c14\u4f53\u548c\u7126\u6cb9\u7684\u6837\u54c1");
    model.result().evaluationGroup("eg1").feature("int2").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int2").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int2").selection().set(4);
    model.result().evaluationGroup("eg1").feature("int2").setIndex("expr", "tcs.ntflux_w_g+tcs.ntflux_w_t", 0);
    model.result().evaluationGroup("eg1").feature("int2")
         .setIndex("descr", "\u4e0d\u542b\u6c14\u4f53\u548c\u7126\u6cb9", 0);
    model.result().evaluationGroup("eg1").feature("int2").set("dataseries", "integral");
    model.result().evaluationGroup("eg1").feature("int2").set("dataseriescumulative", true);
    model.result().evaluationGroup("eg1").create("int3", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int3").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int3").selection().set(1);
    model.result().evaluationGroup("eg1").feature("int3").label("\u4e2d\u95f4\u4f53\u548c\u70ad");
    model.result().evaluationGroup("eg1").feature("int3").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int3").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int3").setIndex("expr", "rho_is + rho_c", 0);
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "\u4e2d\u95f4\u4f53\u548c\u70ad", 0);
    model.result().evaluationGroup("eg1").create("int4", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int4").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int4").label("\u6728\u6750");
    model.result().evaluationGroup("eg1").feature("int4").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int4").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int4").selection().set(1);
    model.result().evaluationGroup("eg1").feature("int4").setIndex("expr", "rho_w", 0);
    model.result().evaluationGroup("eg1").feature("int4").setIndex("descr", "\u6728\u6750", 0);
    model.result().evaluationGroup("eg1").set("generalexpr", "(int1+int2+int3+int4)");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").set("generalexpr", "(int1+int2+int3+int4)/0.0026954");
    model.result().evaluationGroup("eg1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "resultTable");
    model.func("int1").setIndex("argunit", "s", 0);
    model.func().duplicate("int2", "int1");
    model.func("int2").set("resultTable", "tbl2");
    model.func("int2").setIndex("fununit", "K", 0);
    model.func().duplicate("int3", "int2");
    model.func("int3").set("resultTable", "tbl3");
    model.func().duplicate("int4", "int3");
    model.func("int4").set("resultTable", "tbl4");

    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").label("\u76ee\u6807");
    model.component("comp1").variable("var5").set("obj1", "(domY-int1(t))^2");
    model.component("comp1").variable("var5").set("obj2", "(T_surface-int2(t))^2/Tdelta^2");
    model.component("comp1").variable("var5").set("obj3", "(T_middle-int3(t))^2/Tdelta^2");
    model.component("comp1").variable("var5").set("obj4", "(T_center-int4(t))^2/Tdelta^2");

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "obj", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "objt*tmax-obj1-obj2-obj3-obj4", 0, 0);

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/tcs", true);
    model.study("std2").feature("time").setSolveFor("/physics/dl", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/dode", true);
    model.study("std2").feature("time").setSolveFor("/physics/ge", true);
    model.study("std2").label("\u7814\u7a76 2 \u53c2\u6570\u4f30\u8ba1");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "snopt");
    model.study("std2").feature("opt").set("opttolinner", 0.01);
    model.study("std2").feature("opt").set("optobj", new String[]{"comp1.obj"});
    model.study("std2").feature("opt").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj"});
    model.study("std2").feature("opt").set("objectivescaling", "init");
    model.study("std2").feature("opt").setIndex("pname", "A_c", 0);
    model.study("std2").feature("opt").setIndex("initval", "1.38e10[1/s]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "A_c", 0);
    model.study("std2").feature("opt").setIndex("initval", "1.38e10[1/s]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "A_c2", 1);
    model.study("std2").feature("opt").setIndex("initval", "1.00e5[1/s]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "A_c2", 1);
    model.study("std2").feature("opt").setIndex("initval", "1.00e5[1/s]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "A_g", 2);
    model.study("std2").feature("opt").setIndex("initval", "4.38e9[1/s]", 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("pname", "A_g", 2);
    model.study("std2").feature("opt").setIndex("initval", "4.38e9[1/s]", 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("pname", "A_g2", 3);
    model.study("std2").feature("opt").setIndex("initval", "4.28e6[1/s]", 3);
    model.study("std2").feature("opt").setIndex("scale", 1, 3);
    model.study("std2").feature("opt").setIndex("lbound", "", 3);
    model.study("std2").feature("opt").setIndex("ubound", "", 3);
    model.study("std2").feature("opt").setIndex("pname", "A_g2", 3);
    model.study("std2").feature("opt").setIndex("initval", "4.28e6[1/s]", 3);
    model.study("std2").feature("opt").setIndex("scale", 1, 3);
    model.study("std2").feature("opt").setIndex("lbound", "", 3);
    model.study("std2").feature("opt").setIndex("ubound", "", 3);
    model.study("std2").feature("opt").setIndex("pname", "A_is_opt", 0);
    model.study("std2").feature("opt").setIndex("lbound", "A_is_opt_lower", 0);
    model.study("std2").feature("opt").setIndex("ubound", "A_is_opt_upper", 0);
    model.study("std2").feature("opt").setIndex("pname", "DH_c_opt", 1);
    model.study("std2").feature("opt").setIndex("lbound", "DH_c_opt_lower", 1);
    model.study("std2").feature("opt").setIndex("ubound", "DH_c_opt_upper", 1);
    model.study("std2").feature("opt").setIndex("pname", "DH_t_opt", 2);
    model.study("std2").feature("opt").setIndex("lbound", "DH_t_opt_lower", 2);
    model.study("std2").feature("opt").setIndex("ubound", "DH_t_opt_upper", 2);
    model.study("std2").feature("opt").setIndex("pname", "hconv_opt", 3);
    model.study("std2").feature("opt").setIndex("lbound", "hconv_opt_lower", 3);
    model.study("std2").feature("opt").setIndex("ubound", "hconv_opt_upper", 3);
    model.study("std2").feature("opt").set("probesel", "none");
    model.study("std2").feature("time").set("tlist", "range(0,tmax/120,tmax)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_p").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_rho_c").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_rho_c").set("scaleval", "rho_w_init");
    model.sol("sol2").feature("v1").feature("comp1_rho_is").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_rho_is").set("scaleval", "rho_w_init");
    model.sol("sol2").feature("v1").feature("comp1_rho_w").set("scalemethod", "init");
    model.sol("sol2").feature("v1").feature("comp1_T").set("scalemethod", "init");
    model.sol("sol2").feature("v1").feature("comp1_w_g").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_w_g").set("scaleval", 0.1);
    model.sol("sol2").feature("v1").feature("comp1_w_t").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_w_t").set("scaleval", 0.1);
    model.sol("sol2").feature("o1").feature("t1").set("tout", "tsteps");
    model.sol("sol2").feature("o1").feature("t1").feature("aDef").set("convinfo", false);
    model.sol("sol2").feature("o1").feature("t1").feature("d1").set("linsolver", "pardiso");

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2")
         .label("\u6b63\u6f14\u6a21\u578b\u548c\u5b9e\u9a8c\u6570\u636e\uff1aT_surface \u548c T_middle");
    model.result("pg2").run();
    model.result("pg2").feature().remove("tblp1");
    model.result("pg2").feature().remove("tblp4");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").label("\u8868\u9762\u6e29\u5ea6 (exp)");
    model.result("pg2").run();
    model.result("pg2").feature("tblp3").label("\u4e2d\u95f4\u6e29\u5ea6 (exp)");
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").label("\u8868\u9762\u6e29\u5ea6\uff08\u6b63\u6f14\u6a21\u578b\uff09");
    model.result("pg2").feature("ptgr1").set("data", "dset1");
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").feature("ptgr1").set("expr", "T");
    model.result("pg2").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg2").feature("ptgr1").set("linecolor", "magenta");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg2").feature("ptgr1").set("autopoint", false);
    model.result("pg2").feature("ptgr1").set("autosolution", false);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").label("\u8868\u9762\u6e29\u5ea6\uff08\u4f18\u5316\u6a21\u578b\uff09");
    model.result("pg2").feature("ptgr2").set("data", "dset2");
    model.result("pg2").feature("ptgr2").set("linestyle", "solid");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").label("\u4e2d\u95f4\u6e29\u5ea6\uff08\u6b63\u6f14\u6a21\u578b\uff09");
    model.result("pg2").feature("ptgr3").selection().set(3);
    model.result("pg2").feature("ptgr3").set("linecolor", "black");
    model.result("pg2").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("data", "dset2");
    model.result("pg2").feature("ptgr4").label("\u4e2d\u95f4\u6e29\u5ea6\uff08\u4f18\u5316\u6a21\u578b\uff09");
    model.result("pg2").feature("ptgr4").set("linestyle", "solid");
    model.result("pg2").run();
    model.result("pg2").set("twoyaxes", false);
    model.result("pg2").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg2").set("legendlayout", "outside");
    model.result("pg2").set("legendposoutside", "bottom");
    model.result("pg3").run();
    model.result("pg3").feature().remove("tblp2");
    model.result("pg3").feature().remove("tblp3");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3")
         .label("\u4f18\u5316\u7684\u6b63\u6f14\u6a21\u578b\u548c\u5b9e\u9a8c\u6570\u636e\uff1aY \u548c T_center");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1")
         .label("\u5f52\u4e00\u5316\u7684\u56fa\u4f53\u8d28\u91cf\uff08\u6b63\u6f14\u6a21\u578b\uff09");
    model.result("pg3").feature("glob1").set("data", "dset1");
    model.result("pg3").feature("glob1").set("expr", new String[]{"domY"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u57df\u63a2\u9488 Y"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg3").feature("glob1").set("linestyle", "dashed");
    model.result("pg3").feature("glob1").set("linecolor", "red");
    model.result("pg3").feature("glob1").set("autoplotlabel", true);
    model.result("pg3").feature("glob1").set("autosolution", false);
    model.result("pg3").feature("glob1").set("autodescr", false);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2")
         .label("\u5f52\u4e00\u5316\u7684\u56fa\u4f53\u8d28\u91cf\uff08\u4f18\u5316\u6a21\u578b\uff09");
    model.result("pg3").feature("glob2").set("data", "dset2");
    model.result("pg3").feature("glob2").set("linestyle", "solid");
    model.result("pg3").run();
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").label("\u4e2d\u5fc3\u6e29\u5ea6\uff08\u6b63\u6f14\u6a21\u578b\uff09");
    model.result("pg3").feature("ptgr1").set("data", "dset1");
    model.result("pg3").feature("ptgr1").set("expr", "T");
    model.result("pg3").feature("ptgr1").set("plotonsecyaxis", true);
    model.result("pg3").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg3").feature("ptgr1").set("linecolor", "blue");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg3").feature("ptgr1").set("autopoint", false);
    model.result("pg3").feature("ptgr1").set("autosolution", false);
    model.result("pg3").feature("ptgr1").selection().set(3);
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").label("\u4e2d\u5fc3\u6e29\u5ea6\uff08\u4f18\u5316\u6a21\u578b\uff09");
    model.result("pg3").feature("ptgr2").set("data", "dset2");
    model.result("pg3").feature("ptgr2").set("linestyle", "solid");
    model.result("pg3").run();

    model.study("std2").feature("opt").set("plot", true);
    model.study("std2").feature("opt").set("plotgroup", "pg3");
    model.study("std2").createAutoSequences("all");
    model.study("std2").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std2").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol2").runAll();

    model.study("std2").feature("opt").set("probewindow", "");

    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u4f18\u5316\u7684\u53c2\u6570\u503c");
    model.result().evaluationGroup("eg2").set("data", "dset2");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"obj", "A_is"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj", "\u9891\u7387\u56e0\u5b50\uff0cw -> is (\u4e2d\u95f4\u56fa\u4f53)"});
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"obj", "A_is", "DH_c"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj", "\u9891\u7387\u56e0\u5b50\uff0cw -> is (\u4e2d\u95f4\u56fa\u4f53)", "\u53cd\u5e94\u70ed\uff0c\u4e2d\u95f4\u56fa\u4f53 -> \u70ad"});
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"obj", "A_is", "DH_c", "DH_t"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj", "\u9891\u7387\u56e0\u5b50\uff0cw -> is (\u4e2d\u95f4\u56fa\u4f53)", "\u53cd\u5e94\u70ed\uff0c\u4e2d\u95f4\u56fa\u4f53 -> \u70ad", "\u53cd\u5e94\u70ed\uff0c\u6728\u6750 -> \u7126\u6cb9"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("expr", new String[]{"obj", "A_is", "DH_c", "DH_t", "hconv"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf obj", "\u9891\u7387\u56e0\u5b50\uff0cw -> is (\u4e2d\u95f4\u56fa\u4f53)", "\u53cd\u5e94\u70ed\uff0c\u4e2d\u95f4\u56fa\u4f53 -> \u70ad", "\u53cd\u5e94\u70ed\uff0c\u6728\u6750 -> \u7126\u6cb9", "\u5916\u90e8\u5bf9\u6d41\u6362\u70ed\u7cfb\u6570"});
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("\u76ee\u6807");
    model.result().evaluationGroup("eg3").set("data", "dset2");
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "obj1", 0);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "obj2", 1);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "obj3", 2);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "obj4", 3);
    model.result().evaluationGroup("eg3").feature("gev1").set("dataseries", "integral");
    model.result().evaluationGroup("eg3").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u56fa\u4f53\u7269\u8d28");
    model.result("pg4").set("legendpos", "rightdouble");
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").set("arrayshape", "square");
    model.result("pg4").set("arrayplane", "xz");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0, 1, 1);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u534a\u7403\u4f53");
    model.result().dataset("rev1").set("data", "mir1");
    model.result().dataset("rev1").set("revangle", 180);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("arraydim", "2");
    model.result("pg4").feature("surf1").label("150s \u6728\u6750");
    model.result("pg4").feature("surf1").set("data", "rev1");
    model.result("pg4").feature("surf1").setIndex("looplevel", "interp", 0);
    model.result("pg4").feature("surf1").set("interp", new int[]{150});
    model.result("pg4").feature("surf1").set("expr", "rho_w/rho_w_init");
    model.result().colorTable().create("ctbl1", "ColorTable");
    model.result().colorTable("ctbl1").label("\u6728\u6750");
    model.result().colorTable("ctbl1").setIndex("data", 1, 0, 0);
    model.result().colorTable("ctbl1").setIndex("data", 0.72, 0, 1);
    model.result().colorTable("ctbl1").setIndex("data", 0.075, 0, 2);
    model.result().colorTable("ctbl1").setIndex("data", 20, 0, 3);
    model.result().colorTable("ctbl1").setIndex("data", 1, 1, 0);
    model.result().colorTable("ctbl1").setIndex("data", 1, 1, 1);
    model.result().colorTable("ctbl1").setIndex("data", 1, 1, 2);
    model.result().colorTable("ctbl1").setIndex("data", 1, 1, 3);
    model.result().colorTable("ctbl1").setIndex("data", 1, 2, 0);
    model.result().colorTable("ctbl1").setIndex("data", 1, 2, 1);
    model.result().colorTable("ctbl1").setIndex("data", 1, 2, 2);
    model.result().colorTable("ctbl1").setIndex("data", 5, 2, 3);
    model.result().colorTable("ctbl1").setIndex("data", 0.9, 3, 0);
    model.result().colorTable("ctbl1").setIndex("data", 0.9, 3, 1);
    model.result().colorTable("ctbl1").setIndex("data", 0.9, 3, 2);
    model.result().colorTable("ctbl1").setIndex("data", 0.1, 3, 3);
    model.result().colorTable().create("ctbl2", "ColorTable");
    model.result().colorTable("ctbl2").label("\u4e2d\u95f4\u4f53");
    model.result().colorTable("ctbl2").setIndex("data", 0.77, 0, 0);
    model.result().colorTable("ctbl2").setIndex("data", 0.22, 0, 1);
    model.result().colorTable("ctbl2").setIndex("data", "0.20", 0, 2);
    model.result().colorTable("ctbl2").setIndex("data", 20, 0, 3);
    model.result().colorTable("ctbl2").setIndex("data", 1, 1, 0);
    model.result().colorTable("ctbl2").setIndex("data", 1, 1, 1);
    model.result().colorTable("ctbl2").setIndex("data", 1, 1, 2);
    model.result().colorTable("ctbl2").setIndex("data", 1, 1, 3);
    model.result().colorTable("ctbl2").setIndex("data", 1, 2, 0);
    model.result().colorTable("ctbl2").setIndex("data", 1, 2, 1);
    model.result().colorTable("ctbl2").setIndex("data", 1, 2, 2);
    model.result().colorTable("ctbl2").setIndex("data", 5, 2, 3);
    model.result().colorTable("ctbl2").setIndex("data", 0.9, 3, 0);
    model.result().colorTable("ctbl2").setIndex("data", 0.9, 3, 1);
    model.result().colorTable("ctbl2").setIndex("data", 0.9, 3, 2);
    model.result().colorTable("ctbl2").setIndex("data", 0.1, 3, 3);
    model.result().colorTable().create("ctbl3", "ColorTable");
    model.result().colorTable("ctbl3").label("\u70ad");
    model.result().colorTable("ctbl3").setIndex("data", 0.3, 0, 0);
    model.result().colorTable("ctbl3").setIndex("data", 0.13, 0, 1);
    model.result().colorTable("ctbl3").setIndex("data", 0.06, 0, 2);
    model.result().colorTable("ctbl3").setIndex("data", 20, 0, 3);
    model.result().colorTable("ctbl3").setIndex("data", 1, 1, 0);
    model.result().colorTable("ctbl3").setIndex("data", 1, 1, 1);
    model.result().colorTable("ctbl3").setIndex("data", 1, 1, 2);
    model.result().colorTable("ctbl3").setIndex("data", 1, 1, 3);
    model.result().colorTable("ctbl3").setIndex("data", 1, 2, 0);
    model.result().colorTable("ctbl3").setIndex("data", 1, 2, 1);
    model.result().colorTable("ctbl3").setIndex("data", 1, 2, 2);
    model.result().colorTable("ctbl3").setIndex("data", 5, 2, 3);
    model.result().colorTable("ctbl3").setIndex("data", 0.9, 3, 0);
    model.result().colorTable("ctbl3").setIndex("data", 0.9, 3, 1);
    model.result().colorTable("ctbl3").setIndex("data", 0.9, 3, 2);
    model.result().colorTable("ctbl3").setIndex("data", 0.1, 3, 3);
    model.result("pg4").feature("surf1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "\u6728\u6750");
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature("surf2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").label("150s \u4e2d\u95f4\u4f53");
    model.result("pg4").feature("surf2").set("expr", "rho_is/rho_w_init");
    model.result("pg4").feature("surf2").set("colortable", "\u4e2d\u95f4\u4f53");
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").feature("surf3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").label("150s \u70ad");
    model.result("pg4").feature("surf3").set("expr", "rho_c/rho_w_init");
    model.result("pg4").feature("surf3").set("colortable", "\u70ad");
    model.result("pg4").feature("surf3").set("manualindexing", true);
    model.result("pg4").feature("surf3").set("colindex", 2);
    model.result("pg4").feature("surf1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf4", "surf1");
    model.result("pg4").feature("surf4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf4").label("270s \u6728\u6750");
    model.result("pg4").feature("surf4").set("interp", new int[]{270});
    model.result("pg4").feature("surf4").set("inheritplot", "surf1");
    model.result("pg4").feature("surf4").set("manualindexing", true);
    model.result("pg4").feature("surf4").set("rowindex", 1);
    model.result("pg4").feature("surf2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf5", "surf2");
    model.result("pg4").feature("surf5").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf5").label("270s \u4e2d\u95f4\u4f53");
    model.result("pg4").feature("surf5").set("interp", new int[]{270});
    model.result("pg4").feature("surf5").set("inheritplot", "surf2");
    model.result("pg4").feature("surf5").set("manualindexing", true);
    model.result("pg4").feature("surf5").set("rowindex", 1);
    model.result("pg4").feature("surf5").set("colindex", 1);
    model.result("pg4").feature("surf3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf6", "surf3");
    model.result("pg4").feature("surf6").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf6").label("270s \u70ad");
    model.result("pg4").feature("surf6").set("interp", new int[]{270});
    model.result("pg4").feature("surf6").set("inheritplot", "surf3");
    model.result("pg4").feature("surf6").set("rowindex", 1);
    model.result("pg4").feature("surf4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf7", "surf4");
    model.result("pg4").feature("surf7").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf7").label("410 s \u6728\u6750");
    model.result("pg4").feature("surf7").set("interp", new int[]{410});
    model.result("pg4").feature("surf7").set("rowindex", 2);
    model.result("pg4").feature("surf5").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf8", "surf5");
    model.result("pg4").feature("surf8").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf8").label("410 s \u4e2d\u95f4\u4f53");
    model.result("pg4").feature("surf8").set("interp", new int[]{410});
    model.result("pg4").feature("surf8").set("rowindex", 2);
    model.result("pg4").feature("surf6").set("arraydim", "2");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf9", "surf6");
    model.result("pg4").feature("surf9").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf9").label("410 s \u70ad");
    model.result("pg4").feature("surf9").set("interp", new int[]{410});
    model.result("pg4").feature("surf9").set("rowindex", 2);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("arraydim", "2");
    model.result("pg4").feature("ann1").label("\u6728\u6750");
    model.result("pg4").feature("ann1").set("data", "rev1");
    model.result("pg4").feature("ann1").set("text", "$\\frac{\\rho_{\\omega}}{\\rho_{\\omega,0}}$");
    model.result("pg4").feature("ann1").set("latexmarkup", true);
    model.result("pg4").feature("ann1").set("poszexpr", -0.02);
    model.result("pg4").feature("ann1").set("showpoint", false);
    model.result("pg4").feature("ann1").set("anchorpoint", "center");
    model.result("pg4").feature("ann1").set("manualindexing", true);
    model.result("pg4").feature().duplicate("ann2", "ann1");
    model.result("pg4").feature("ann2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann2").label("\u4e2d\u95f4\u4f53");
    model.result("pg4").feature("ann2").set("text", "$\\frac{\\rho_{is}}{\\rho_{\\omega,0}}$");
    model.result("pg4").feature("ann2").set("colindex", 1);
    model.result("pg4").feature().duplicate("ann3", "ann2");
    model.result("pg4").feature("ann3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann3").label("\u70ad");
    model.result("pg4").feature("ann3").set("text", "$\\frac{\\rho_{c}}{\\rho_{\\omega,0}}$");
    model.result("pg4").feature("ann3").set("colindex", 2);
    model.result("pg4").feature("ann1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("ann4", "ann1");
    model.result("pg4").feature("ann4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann4").label("150 s");
    model.result("pg4").feature("ann4").set("text", "150 s");
    model.result("pg4").feature("ann4").set("poszexpr", 0);
    model.result("pg4").feature("ann4").set("posxexpr", -0.025);
    model.result("pg4").feature().duplicate("ann5", "ann4");
    model.result("pg4").feature("ann5").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann5").label("270 s");
    model.result("pg4").feature("ann5").set("text", "270 s");
    model.result("pg4").feature("ann5").set("rowindex", 1);
    model.result("pg4").feature().duplicate("ann6", "ann5");
    model.result("pg4").feature("ann6").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann6").label("410 s");
    model.result("pg4").feature("ann6").set("text", "410 s");
    model.result("pg4").feature("ann6").set("rowindex", 2);
    model.result("pg4").run();
    model.result("pg4").set("view", "view2");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("150 s \u65f6\u7684 T\u3001Qmass \u548c Q");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", "interp", 0);
    model.result("pg5").set("interp", new int[]{150});
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").set("legendpos", "rightdouble");
    model.result("pg5").set("plotarrayenable", true);
    model.result("pg5").set("arrayshape", "square");
    model.result("pg5").set("order", "columnmajor");
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("arraydim", "2");
    model.result("pg5").feature("ann1").label("\u6e29\u5ea6");
    model.result("pg5").feature("ann1").set("text", "$T$");
    model.result("pg5").feature("ann1").set("latexmarkup", true);
    model.result("pg5").feature("ann1").set("showpoint", false);
    model.result("pg5").feature("ann1").set("anchorpoint", "center");
    model.result("pg5").feature("ann1").set("manualindexing", true);
    model.result("pg5").feature("ann1").set("posxexpr", 0.005);
    model.result("pg5").feature("ann1").set("posyexpr", 0.0135);
    model.result("pg5").feature().duplicate("ann2", "ann1");
    model.result("pg5").feature("ann2").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("ann2").label("\u8d28\u91cf\u6e90");
    model.result("pg5").feature("ann2").set("text", "$Q_{mass}$");
    model.result("pg5").feature("ann2").set("rowindex", 1);
    model.result("pg5").feature().duplicate("ann3", "ann2");
    model.result("pg5").feature("ann3").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("ann3").label("\u70ed\u6e90");
    model.result("pg5").feature("ann3").set("text", "$Q$");
    model.result("pg5").feature("ann3").set("rowindex", 2);
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("arraydim", "2");
    model.result("pg5").feature("surf1").label("T");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("manualindexing", true);
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").feature("surf2").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").label("dl.Qm");
    model.result("pg5").feature("surf2").set("expr", "dl.Qm");
    model.result("pg5").feature("surf2").set("colortable", "Crocus");
    model.result("pg5").feature("surf2").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("surf2").set("rowindex", 1);
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").feature("surf3").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").label("Q");
    model.result("pg5").feature("surf3").set("expr", "Q");
    model.result("pg5").feature("surf3").set("colortable", "Avicularia");
    model.result("pg5").feature("surf3").set("rowindex", 2);
    model.result("pg5").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("270 s \u65f6\u7684 T\u3001Qmass \u548c Q");
    model.result("pg6").set("interp", new int[]{270});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("433 s \u65f6\u7684 T\u3001Qmass \u548c Q");
    model.result("pg7").set("interp", new int[]{433});
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8")
         .label("270 s \u65f6\u7684\u538b\u529b\u3001\u901f\u5ea6\u3001\u5b54\u9699\u7387\u548c\u5f52\u4e00\u5316\u56fa\u4f53\u8d28\u91cf");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", "interp", 0);
    model.result("pg8").set("interp", new int[]{270});
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").set("legendpos", "rightdouble");
    model.result("pg8").set("plotarrayenable", true);
    model.result("pg8").set("arrayshape", "square");
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").set("arraydim", "2");
    model.result("pg8").feature("ann1").label("\u76f8\u5bf9\u538b\u529b");
    model.result("pg8").feature("ann1").set("text", "$p/p_{ref}$");
    model.result("pg8").feature("ann1").set("latexmarkup", true);
    model.result("pg8").feature("ann1").set("posxexpr", 0.005);
    model.result("pg8").feature("ann1").set("posyexpr", 0.0135);
    model.result("pg8").feature("ann1").set("showpoint", false);
    model.result("pg8").feature("ann1").set("anchorpoint", "center");
    model.result("pg8").feature("ann1").set("manualindexing", true);
    model.result("pg8").feature().duplicate("ann2", "ann1");
    model.result("pg8").feature("ann2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("ann2").label("\u603b\u8fbe\u897f\u901f\u5ea6\u5927\u5c0f");
    model.result("pg8").feature("ann2").set("text", "$U$");
    model.result("pg8").feature("ann2").set("colindex", 1);
    model.result("pg8").feature().duplicate("ann3", "ann2");
    model.result("pg8").feature("ann3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("ann3").label("\u5b54\u9699\u7387");
    model.result("pg8").feature("ann3").set("text", "$\\epsilon$");
    model.result("pg8").feature("ann3").set("rowindex", 1);
    model.result("pg8").feature("ann3").set("colindex", 0);
    model.result("pg8").feature().duplicate("ann4", "ann3");
    model.result("pg8").feature("ann4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("ann4").label("\u5f52\u4e00\u5316\u56fa\u4f53\u8d28\u91cf");
    model.result("pg8").feature("ann4").set("text", "$Y$");
    model.result("pg8").feature("ann4").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").feature("surf1").label("dl.pA/dl.pref");
    model.result("pg8").feature("surf1").set("expr", "dl.pA/dl.pref");
    model.result("pg8").feature("surf1").set("colortable", "Iodinea");
    model.result("pg8").feature("surf1").set("manualindexing", true);
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").feature("surf2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").label("dl.U");
    model.result("pg8").feature("surf2").set("expr", "dl.U");
    model.result("pg8").feature("surf2").set("colortable", "Acanthaster");
    model.result("pg8").feature("surf2").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").set("arraydim", "2");
    model.result("pg8").feature("arws1").label("dl.u");
    model.result("pg8").feature("arws1").setIndex("expr", "dl.u", 0);
    model.result("pg8").feature("arws1").set("expr", new String[]{"dl.u", "dl.w"});
    model.result("pg8").feature("arws1").set("xnumber", 10);
    model.result("pg8").feature("arws1").set("ynumber", 10);
    model.result("pg8").feature("arws1").set("arrowtype", "cone");
    model.result("pg8").feature("arws1").set("arrowlength", "normalized");
    model.result("pg8").feature("arws1").set("arrowbase", "center");
    model.result("pg8").feature("arws1").set("scaleactive", true);
    model.result("pg8").feature("arws1").set("scale", 0.14);
    model.result("pg8").feature("arws1").set("color", "black");
    model.result("pg8").feature("arws1").set("manualindexing", true);
    model.result("pg8").feature("arws1").set("colindex", 1);
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf1");
    model.result("pg8").feature("surf3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").label("epsilon");
    model.result("pg8").feature("surf3").set("expr", "epsilon");
    model.result("pg8").feature("surf3").set("colortable", "Metasepia");
    model.result("pg8").feature("surf3").set("colortabletrans", "reverse");
    model.result("pg8").feature("surf3").set("rowindex", 1);
    model.result("pg8").feature().duplicate("surf4", "surf3");
    model.result("pg8").feature("surf4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf4").label("Y");
    model.result("pg8").feature("surf4").set("expr", "Y");
    model.result("pg8").feature("surf4").set("colortable", "Cynanthus");
    model.result("pg8").feature("surf4").set("colortabletrans", "none");
    model.result("pg8").feature("surf4").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().dataset().duplicate("rev2", "rev1");
    model.result().dataset("rev2").label("\u70ed\u6e90");
    model.result().dataset("rev2").set("revangle", 230);
    model.result().dataset().duplicate("rev3", "rev2");
    model.result().dataset("rev3").label("\u6837\u54c1");
    model.result().dataset("rev3").set("revangle", 270);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").set("data", "rev2");
    model.result("pg9").setIndex("looplevel", "interp", 0);
    model.result("pg9").set("interp", new int[]{337});
    model.result("pg9").set("showlegends", false);
    model.result("pg9").set("titletype", "none");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u6728\u6750\u58f3");
    model.result("pg9").feature("surf1").set("expr", "1");
    model.result("pg9").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg9").feature("surf1").feature("mtrl1").set("family", "custom");
    model.result("pg9").feature("surf1").feature("mtrl1").set("normalnoisebrush", "3");
    model.result("pg9").feature("surf1").feature("mtrl1").set("colornoise", true);
    model.result("pg9").feature("surf1").feature("mtrl1").set("colornoisebrush", "3");
    model.result("pg9").run();
    model.result("pg9").set("edges", false);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg9").feature("surf1").feature("sel1").set("evalstartcap", false);
    model.result("pg9").feature("surf1").feature("sel1").set("evalendcap", false);
    model.result("pg9").run();
    model.result("pg9").create("surf2", "Surface");
    model.result("pg9").feature("surf2").label("\u5185\u5e73\u9762");
    model.result("pg9").feature("surf2").set("expr", "Q");
    model.result("pg9").feature("surf2").create("sel1", "Selection");
    model.result("pg9").feature("surf2").feature("sel1").selection().set(1);
    model.result("pg9").feature("surf2").feature("sel1").set("evalmantle", false);
    model.result("pg9").run();
    model.result("pg9").feature("surf2").set("colortable", "Avicularia");
    model.result("pg9").feature("surf2").set("colorscalemode", "linearsymmetric");
    model.result("pg9").run();
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg9").feature("arws1").set("plotcomp", "tangential");
    model.result("pg9").feature("arws1").set("scaleactive", true);
    model.result("pg9").feature("arws1").set("scale", 0.7);
    model.result("pg9").feature("arws1").set("arrowtype", "arrowhead");
    model.result("pg9").feature("arws1").set("color", "gray");
    model.result("pg9").feature("arws1").create("sel1", "Selection");
    model.result("pg9").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg9").feature("arws1").feature("sel1").set("evalmantle", false);
    model.result("pg9").feature("arws1").feature("sel1").set("evalendcap", false);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").label("\u70ed\u6ce2");
    model.result("pg9").set("view", "view4");
    model.result("pg9").run();

    model.view("view4").camera().set("orthoscale", 0.060288891196250916);
    model.view("view4").camera().set("zoomanglefull", 14.004934310913086);
    model.view("view4").camera()
         .set("position", new double[]{-0.013599690049886703, -0.189704030752182, -0.11260087788105011});
    model.view("view4").camera()
         .set("target", new double[]{3.8063153624534607E-6, 0.0014843493700027466, -2.086162567138672E-7});
    model.view("view4").camera()
         .set("up", new double[]{0.22215645015239716, -0.5064829587936401, 0.8331350684165955});
    model.view("view4").camera().set("rotationpoint", new double[]{2.2888602688908577E-4, 0.0014847125858068466, 0});
    model.view("view4").camera().set("viewoffset", new double[]{-0.003627249039709568, -0.0039122651323121056});
    model.view("view4").light("lgt1").set("lightdirection", new int[]{2, 8, 1});
    model.view("view4").light("lgt1").set("intensity", 0.85);
    model.view("view4").light("lgt2").set("lightdirection", new int[]{-1, -2, -1});
    model.view("view4").light("lgt2").set("intensity", 0.9);
    model.view("view4").light("lgt3").set("lightdirection", new int[]{-1, -1, 0});
    model.view("view4").light("lgt3").set("intensity", 0.5);
    model.view("view4").set("showgrid", false);
    model.view("view4").set("showaxisorientation", false);
    model.view("view4").set("ssao", true);
    model.view("view4").set("flooreffect", true);
    model.view("view4").set("locked", true);

    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u6837\u54c1");
    model.result("pg10").set("data", "rev3");
    model.result("pg10").run();
    model.result("pg10").feature().remove("surf2");
    model.result("pg10").feature().remove("arws1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("surf1").feature("sel1").set("evalstartcap", true);
    model.result("pg10").feature("surf1").feature("sel1").set("evalendcap", true);
    model.result("pg10").run();
    model.result("pg10").feature("surf1").label("\u6728\u6750");
    model.result("pg10").run();
    model.result("pg10").set("view", "view3");
    model.result("pg10").run();
    model.result("pg9").run();

    model.title("\u5177\u6709\u65f6\u95f4\u79ef\u5206\u76ee\u6807\u7684\u6728\u6750\u70ed\u89e3");

    model
         .description("\u5398\u7c73\u7ea7\u6728\u6750\u9897\u7c92\u7684\u70ed\u89e3\u662f\u4e00\u4e2a\u6d89\u53ca\u8d28\u91cf\u4f20\u9012\u3001\u6d41\u4f53\u6d41\u52a8\u548c\u4f20\u70ed\u7684\u5168\u8026\u5408\u591a\u7269\u7406\u573a\u95ee\u9898\u3002\n\n\u672c\u793a\u4f8b\u6a21\u578b\u7531\u4e24\u90e8\u5206\u7ec4\u6210\u3002\u7b2c\u4e00\u90e8\u5206\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u4e00\u4e2a\u63cf\u8ff0\u591a\u5b54\u5404\u5411\u5f02\u6027\u6728\u7403\u70ed\u89e3\u7684\u6a21\u578b\uff0c\u5176\u4e2d\u91c7\u7528\u7531\u4e94\u79cd\u4f2a\u7269\u8d28\u548c\u4e94\u4e2a\u53cd\u5e94\u7ec4\u6210\u7684\u53cd\u5e94\u65b9\u6848\u3002\n\n\u7b2c\u4e8c\u90e8\u5206\u4f7f\u7528\u53c2\u6570\u4f30\u8ba1\u65b9\u6cd5\u57fa\u4e8e\u5b9e\u9a8c\u6570\u636e\u5bf9\u6a21\u578b\u8fdb\u884c\u4f18\u5316\uff0c\u4f30\u8ba1\u7684\u53c2\u6570\u5305\u62ec\u4e00\u4e2a\u963f\u7d2f\u5c3c\u4e4c\u65af\u5e38\u6570\u3001\u4e24\u4e2a\u53cd\u5e94\u70ed\u548c\u4e00\u4e2a\u5916\u90e8\u4f20\u70ed\u7cfb\u6570\u3002");

    model.label("pyrolysis_wood_odeobj.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
